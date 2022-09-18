package com.study.board.domain.board.com;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.study.board.domain.board.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

@Slf4j
public class ExcelCreate {

    private HSSFWorkbook workbook;
    private Sheet sheet;
    // title style
    private CellStyle styleOfBoardFillFontBlackBold16;
    // 기본 본문 style (center)
    private CellStyle styleOfBoardFontBlack11;
    // 기본 본문 style (center)
    private CellStyle styleOfBoardFontBlack11_Left;
    // 금액  style (right)
    private CellStyle styleOfBoardMoneyFontBlack11;

    private Date date = new Date();

    // 엑셀 생성
    public ExcelCreate(String sheetName) throws Exception {

        try {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet(sheetName);

            //1.셀 스타일 및 폰트 설정
            styleOfBoardFillFontBlackBold16 = workbook.createCellStyle();

            //배경색
            styleOfBoardFillFontBlackBold16.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

            //폰트 설정
            Font fontOfGothicBlackBold16 = workbook.createFont();
            fontOfGothicBlackBold16.setFontName("나눔고딕"); //글씨체
            fontOfGothicBlackBold16.setFontHeight((short)(12*20)); //사이즈
            styleOfBoardFillFontBlackBold16.setFont(fontOfGothicBlackBold16);

            //2.셀 스타일 및 폰트 설정
            CellStyle styleOfBoardFillFontRedBold14 = workbook.createCellStyle();

            //배경색
            styleOfBoardFillFontRedBold14.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

            //폰트 설정
            Font RedBold14 = workbook.createFont();
            RedBold14.setFontName("나눔고딕"); //글씨체
            RedBold14.setFontHeight((short)(14*20)); //사이즈
            styleOfBoardFillFontRedBold14.setFont(RedBold14);

            //3.셀 스타일 및 폰트 설정
            CellStyle styleOfBoardFillFontBlack11 = workbook.createCellStyle();
            styleOfBoardFillFontBlack11.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());

            //폰트 설정
            Font Black11 = workbook.createFont();
            Black11.setFontName("나눔고딕"); //글씨체
            Black11.setFontHeight((short)(11*20)); //사이즈
            styleOfBoardFillFontBlack11.setFont(Black11);

            //4.셀 스타일 및 폰트 설정(일반 텍스트)
            styleOfBoardFontBlack11 = workbook.createCellStyle();

            //폰트 설정 (위 폰트 사용)
            styleOfBoardFontBlack11.setFont(Black11);


            //5.셀 스타일 및 폰트 설정(일반 텍스트)
            styleOfBoardFontBlack11_Left = workbook.createCellStyle();

            //폰트 설정 (위 폰트 사용)
            styleOfBoardFontBlack11_Left.setFont(Black11);


            //6.셀 스타일 및 폰트 설정(금액)
            styleOfBoardMoneyFontBlack11 = workbook.createCellStyle();

            //폰트 설정 (위 폰트 사용)
            styleOfBoardMoneyFontBlack11.setFont(Black11);
            //천단위 쉼표, 금액
            styleOfBoardMoneyFontBlack11.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

        }catch(RuntimeException e){
            log.error("ExcelCreate RuntimeException");
        } catch (Exception e) {
            log.error("authenticate RuntimeException"); //20191010 취약점  e.printStackTrace();
        }

    }

    // 데이타 입력 (기본)
    public void InsertData(List<Board> list, String[] headerArr, String[] headerEnNmArr) throws Exception {

        // 컬럼해더
        try {
            Row row = null;
            Cell cell = null;
            int rowNo = 0;


            row = sheet.createRow((int) rowNo++);

            for(int i = 0; i < headerArr.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(styleOfBoardFillFontBlackBold16);
                cell.setCellValue(headerArr[i]);
            }


            for(Board board : list) {
                row = sheet.createRow(rowNo++);

                for(int i=0;i<headerEnNmArr.length;i++) {

                    if("NO".equals(headerEnNmArr[i])){
                        cell = row.createCell(i);
                        cell.setCellStyle(styleOfBoardFontBlack11);
                        cell.setCellValue(rowNo);
                    }else {

                        String[] enNmArr = headerEnNmArr[i].split(":");
                        String inValue = "";

                        // 2개 이상 값 출력시
                        if(enNmArr.length > 1) {
                            for(Board b : list) {
                                if(b.getSubject() != null) {
                                    inValue = inValue + b.getSubject();
                                }
                            }

                        }else {
                            if(board.getSubject() != null) {
                                inValue = board.getSubject();
                            }
                        }

                        cell = row.createCell(i);
                        cell.setCellStyle(styleOfBoardFontBlack11);
                        cell.setCellValue(inValue);


                        CellType type = cell.getCellType();
                    }
                }
            }

        }catch(RuntimeException e){
            log.error("ExcelCreate RuntimeException");
        } catch (Exception e) {
            log.error("authenticate RuntimeException"); //20191010 취약점  e.printStackTrace();
        }
    }


    // 데이타 입력 ( style 적용)
    public void InsertData(List<Board> list, String[] headerArr, String[] headerEnNmArr, String[] excelStyleArr) throws Exception {

        // 컬럼해더
        try {

            HashMap styleMap = new HashMap();
            if(excelStyleArr != null) {
                for(String style : excelStyleArr) {
                    String[] styleArr = style.split(":");
                    if(styleArr.length == 2) {
                        styleMap.put(styleArr[0], styleArr[1]);
                    }
                }
            }

            Row row = null;
            Cell cell = null;
            int rowNo = 0;

            row = sheet.createRow((int) rowNo++);

            for(int i = 0; i < headerArr.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(styleOfBoardFillFontBlackBold16);
                cell.setCellValue(headerArr[i]);
            }

            for(int i=0; i< list.size(); i++) {
                row = sheet.createRow(rowNo++);

                //System.out.println("map : " + map);
                for(int j=0;j<headerEnNmArr.length;j++) {

                    if("NO".equals(headerEnNmArr[j])){
                        cell = row.createCell(j);
                        cell.setCellStyle(styleOfBoardFontBlack11);
                        cell.setCellValue(rowNo);
                    }else {

                        String headerEnNm = headerEnNmArr[j];
                        String[] enNmArr = headerEnNmArr[j].split(":");
                        String inValue = "";

                        // 2개 이상 값 출력시
                        if(enNmArr.length > 1) {
                            if(j == 0 && list.get(i).getId() != null) {
                                inValue = inValue + list.get(i).getId();
                            } else if (j == 1 && list.get(i).getSubject() != null) {
                                inValue = inValue + list.get(i).getSubject();
                            } else if (j == 2 && list.get(i).getWriter() != null) {
                                inValue = inValue + list.get(i).getWriter();
                            } else if (j == 3 && list.get(i).getCreatedDate() != null) {
                                inValue = inValue + list.get(i).getCreatedDate();
                            } else if (j == 4 && list.get(i).getLastModifiedDate() != null) {
                                inValue = inValue + list.get(i).getLastModifiedDate();
                            }
                        } else {
                            if(j == 0 && list.get(i).getId() != null) {
                                inValue = inValue + list.get(i).getId();
                            } else if (j == 1 && list.get(i).getSubject() != null) {
                                inValue = inValue + list.get(i).getSubject();
                            } else if (j == 2 && list.get(i).getWriter() != null) {
                                inValue = inValue + list.get(i).getWriter();
                            } else if (j == 3 && list.get(i).getCreatedDate() != null) {
                                inValue = inValue + list.get(i).getCreatedDate();
                            } else if (j == 4 && list.get(i).getLastModifiedDate() != null) {
                                inValue = inValue + list.get(i).getLastModifiedDate();
                            }
                        }

                        cell = row.createCell(j);

                        //  금액 style
                        if("amt".equals(styleMap.get(headerEnNm))) {
                            cell.setCellStyle(styleOfBoardMoneyFontBlack11);
                        }else if("title".equals(styleMap.get(headerEnNm))) {
                            cell.setCellValue(inValue);
                            cell.setCellStyle(styleOfBoardFontBlack11_Left);
                        }else {
                            cell.setCellValue(inValue);
                            cell.setCellStyle(styleOfBoardFontBlack11);
                        }
                    }
                }
            }

        }catch(RuntimeException e){
            log.error("ExcelCreate RuntimeException");
        } catch (Exception e) {
            log.error("authenticate RuntimeException");
        }
    }


    // 엑셀 다운로드
    public void excelFileDownload(HttpServletResponse response, String fileName) throws Exception {

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "ATTachment; Filename=" + URLEncoder.encode(fileName + "_" + date.toString().substring(0,9), "UTF-8") + ".xlsx");

        OutputStream fileOut = response.getOutputStream();
        response.resetBuffer();
        response.setBufferSize(1024 * 4);

        workbook.write(fileOut);
        fileOut.close();

    }


    /* 셀 컬럼 설정 (col = 컬넘 넘버 width= 넓이) */
    public void setCellWidth(int col, int width) {
        this.sheet.setColumnWidth(col, width);
    }

    // 특정 셀 설정 (0부터 시작)
    public void setAllCellWidth(int cols, int width) {
        for (int i = 0; i < cols; i++) {
            this.sheet.setColumnWidth(i, width);
        }
    }
    // 넓이 자동 설정
    public void setAutoCellWidth(int cols) {
        for (int i = 0; i < cols; i++) {
            this.sheet.autoSizeColumn(i);
        }
    }

    /* 셀 병합 */
    public void mergeCell(int col1, int row1, int col2, int row2) {
        this.sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
    }

}
