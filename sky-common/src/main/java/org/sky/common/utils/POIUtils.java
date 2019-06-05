package org.sky.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.sky.common.exception.CommonException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * POI工具类
 */
@Slf4j
public class POIUtils {


    /**
     * 导出Excel
     *
     * @param title
     * @param headers
     * @param data
     * @return
     */
    public static Workbook exportExcel(Workbook wb, String title, String[] headers, ExcelData data, HttpServletResponse response) {
        Sheet sheet = wb.createSheet(title);
        Row row0 = sheet.createRow(0);
        // 获取样式
        CellStyle cellStyle = getCellStyle(wb);
        // 标题、第一行信息
        for (int i = 0; i < headers.length; i++) {
            // 默认宽度是2048
            sheet.setColumnWidth(i, 4096);
            Cell cell = row0.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(cellStyle);
        }
        data.setData(wb);
        String flieName = LocalDate.now() + ".xlsx";
        // 取得输出流
        ServletOutputStream stream = null;
        // 清空输出流
        response.reset();
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", flieName));
        // web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            stream = response.getOutputStream();
            if (wb.getNumberOfSheets() > 0) {
                wb.write(stream);
            }
            wb.close();
            stream.close();
        } catch (IOException e) {
            log.error("导出错误：{}，信息：{}", e, e.getMessage());
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    log.error("导出错误：{}，信息：{}", e, e.getMessage());
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.error("导出错误：{}，信息：{}", e, e.getMessage());
                }
            }
        }
        return wb;
    }


    /**
     * 获取Excel样式（边框、背景色）
     *
     * @return
     */
    public static CellStyle getCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置背景色
        style.setFillForegroundColor(IndexedColors.LIME.index);
        // solid 填充  foreground  前景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN); // 下边框
        style.setBorderLeft(BorderStyle.THIN); // 左边框
        style.setBorderTop(BorderStyle.THIN); // 上边框
        style.setBorderRight(BorderStyle.THIN); // 右边框
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);//设置字体大小
        style.setFont(font);
        return style;
    }


    /**
     * 获取Excel样式（仅边框）
     *
     * @return
     */
    public static CellStyle getBorderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN); // 下边框
        style.setBorderLeft(BorderStyle.THIN); // 左边框
        style.setBorderTop(BorderStyle.THIN); // 上边框
        style.setBorderRight(BorderStyle.THIN); // 右边框
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);//设置字体大小
        style.setFont(font);
        return style;
    }


    /**
     * 获取普通单元格数据
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        CellType type = cell.getCellType();
        switch (type) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
        }
        return null;
    }


    /**
     * 获取合并或者普通单元格的值
     *
     * @param row
     * @param column
     * @return
     */
    public static String getCellValue(Row row, int column) {
        Sheet sheet = row.getSheet();
        int rowNum = row.getRowNum();
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (rowNum >= firstRow && rowNum <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }
        return getCellValue(row.getCell(column));
    }


    /**
     * 校验是否是Excel文件
     *
     * @param fileName
     * @throws CommonException
     */
    public static void checkFile(String fileName) throws CommonException {
        if (!StringUtils.isEmpty(fileName) && !(fileName.endsWith(".xlsx"))) {
            throw new CommonException(400, "不是.xlsx文件！");
        }
    }


    /**
     * 回调接口
     */
    public interface ExcelData {

        /**
         * 设置数据
         *
         * @param wb
         * @return
         */
        Workbook setData(Workbook wb);

    }

}


