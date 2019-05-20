package org.sky.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

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
            log.error("销账单导出错误：{}，信息：{}", e, e.getMessage());
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    log.error("销账单导出错误：{}，信息：{}", e, e.getMessage());
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.error("销账单导出错误：{}，信息：{}", e, e.getMessage());
                }
            }
        }
        return wb;
    }


    /**
     * 获取Excel样式（边框、背景色）
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
     * 回调接口
     */
    public interface ExcelData {

        /**
         * 设置数据
         * @param wb
         * @return
         */
        Workbook setData(Workbook wb);

    }

}


