package com.kyuleelim.admincore.common.utils;

import com.kyuleelim.admincore.common.exception.BizException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * ===========================================
 * Project        : com.kyuleelim.admincore.common.utils
 * File Name      : ExcelUtil
 * Author         : pneum
 * Created Date   : 2025-06-23 오후 10:46
 * Updated Date   : 2025-06-23 오후 10:46
 * Description    : Excel download Common Util
 * ===========================================
 */

public class ExcelUtil {

    /**
     * @Method downloadExcel
     * @Description 엑셀 다운로드 공통 유틸
     * @param response
     * @param fileName
     * @param list
     * @param header
     * @param <T>
     */
    public static <T> void downloadExcel(HttpServletResponse response, String fileName, List<T> list, Map<String, String> header) {

        try(
                Workbook workbook = new XSSFWorkbook(); // .xlsx 형식의 엑셀 파일을 위한 객체 생성
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream() // 엑셀 데이터를 임시로 담을 메모리 스트림
        ) {

            // 시트 생성
            Sheet sheet = workbook.createSheet("Sheet1");
            // 행 Index
            int rowIndex = 0;

            // 헤더 생성
            Row headerRow = sheet.createRow(rowIndex++);
            // 열 Index
            int cellIndex = 0;

            for(String headerName: header.values()) {
                Cell cell = headerRow.createCell(cellIndex++);
                // 헤더 명 (컬럼 명) 셋팅
                cell.setCellValue(headerName);
            }

            // 데이터 행 생성
            for(T item: list) {
                // 새로운 행 생성
                Row dataRow = sheet.createRow(rowIndex++);
                // 열 Index 초기화
                cellIndex = 0;

                for (String fieldName : header.keySet()) {
                    Cell cell = dataRow.createCell(cellIndex++);
                    // DTO 객체에서 필드 값 추출
                    Object value = getFieldValue(item, fieldName);

                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }

            // 엑셀 파일 임시 저장
            workbook.write(outputStream);

            // 파일명 인코딩
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

            // HTTP 응답 헤더 설정
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
            
            // 파일 응답 전송
            ServletOutputStream os = response.getOutputStream();
            // 엑셀 데이터 클라이언트에 전송
            outputStream.writeTo(os);
            // 전송 완료
            os.flush();

        } catch (IOException e) {
            throw new BizException("FAIL_TO_DOWNLOAD_EXCEL");
        }
    }

    /**
     * @Method getFieldValue
     * @Description 객체의 필드 값 조회
     * @param obj
     * @param fieldName
     * @return 객체 필드 값
     */
    private static Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = findField(obj.getClass(), fieldName);

            if (field != null) {
                // 프라이빗 필드에도 접근 가능하게 설정
                field.setAccessible(true);
                // 필드 값 추출
                return field.get(obj);
            }
        } catch (Exception ignored) {}

        return null;
    }

    /**
     * @Method findField
     * @Description
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field findField(Class<?> clazz, String fieldName) {
        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // 부모 클래스 필드까지 체크
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}
