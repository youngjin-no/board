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

//    private static final Logger logger = LoggerFactory.getLogger(EgovLoginController.class);

    // 엑셀 생성
    public ExcelCreate(String sheetName) throws Exception {

        try {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet(sheetName);

            //1.셀 스타일 및 폰트 설정
            styleOfBoardFillFontBlackBold16 = workbook.createCellStyle();
            //정렬
//            styleOfBoardFillFontBlackBold16.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
//            styleOfBoardFillFontBlackBold16.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
            //배경색
            styleOfBoardFillFontBlackBold16.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
//            styleOfBoardFillFontBlackBold16.setFillPattern(CellStyle.SOLID_FOREGROUND);
            //테두리 선 (우,좌,위,아래)
//            styleOfBoardFillFontBlackBold16.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontBlackBold16.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontBlackBold16.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontBlackBold16.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //폰트 설정
            Font fontOfGothicBlackBold16 = workbook.createFont();
            fontOfGothicBlackBold16.setFontName("나눔고딕"); //글씨체
            fontOfGothicBlackBold16.setFontHeight((short)(12*20)); //사이즈
//            fontOfGothicBlackBold16.setBoldweight(Font.BOLDWEIGHT_BOLD); //볼드 (굵게)
            styleOfBoardFillFontBlackBold16.setFont(fontOfGothicBlackBold16);

            //2.셀 스타일 및 폰트 설정
            CellStyle styleOfBoardFillFontRedBold14 = workbook.createCellStyle();
            //정렬
//            styleOfBoardFillFontRedBold14.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
//            styleOfBoardFillFontRedBold14.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
            //배경색
            styleOfBoardFillFontRedBold14.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
//            styleOfBoardFillFontRedBold14.setFillPattern(CellStyle.SOLID_FOREGROUND);
            //테두리 선 (우,좌,위,아래)
//            styleOfBoardFillFontRedBold14.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontRedBold14.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontRedBold14.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontRedBold14.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //폰트 설정
            Font RedBold14 = workbook.createFont();
            RedBold14.setFontName("나눔고딕"); //글씨체
//            RedBold14.setColor(HSSFColor.RED.index);
            RedBold14.setFontHeight((short)(14*20)); //사이즈
//            RedBold14.setBoldweight(Font.BOLDWEIGHT_BOLD); //볼드 (굵게)
            styleOfBoardFillFontRedBold14.setFont(RedBold14);

            //3.셀 스타일 및 폰트 설정
            CellStyle styleOfBoardFillFontBlack11 = workbook.createCellStyle();
            //정렬
//            styleOfBoardFillFontBlack11.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
//            styleOfBoardFillFontBlack11.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
            //배경색
            styleOfBoardFillFontBlack11.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
//            styleOfBoardFillFontBlack11.setFillPattern(CellStyle.SOLID_FOREGROUND);
            //테두리 선 (우,좌,위,아래)
//            styleOfBoardFillFontBlack11.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontBlack11.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontBlack11.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFillFontBlack11.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //폰트 설정
            Font Black11 = workbook.createFont();
            Black11.setFontName("나눔고딕"); //글씨체
            Black11.setFontHeight((short)(11*20)); //사이즈
            styleOfBoardFillFontBlack11.setFont(Black11);

            //4.셀 스타일 및 폰트 설정(일반 텍스트)
            styleOfBoardFontBlack11 = workbook.createCellStyle();
            //정렬
//            styleOfBoardFontBlack11.setAlignment(CellStyle.ALIGN_CENTER); //가운데 정렬
//            styleOfBoardFontBlack11.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
            //테두리 선 (우,좌,위,아래)
//            styleOfBoardFontBlack11.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFontBlack11.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFontBlack11.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFontBlack11.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //폰트 설정 (위 폰트 사용)
            styleOfBoardFontBlack11.setFont(Black11);


            //5.셀 스타일 및 폰트 설정(일반 텍스트)
            styleOfBoardFontBlack11_Left = workbook.createCellStyle();
            //정렬
//            styleOfBoardFontBlack11_Left.setAlignment(CellStyle.ALIGN_LEFT); //왼쪽 정렬
//            styleOfBoardFontBlack11_Left.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
            //테두리 선 (우,좌,위,아래)
//            styleOfBoardFontBlack11_Left.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFontBlack11_Left.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFontBlack11_Left.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardFontBlack11_Left.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //폰트 설정 (위 폰트 사용)
            styleOfBoardFontBlack11_Left.setFont(Black11);


            //6.셀 스타일 및 폰트 설정(금액)
            styleOfBoardMoneyFontBlack11 = workbook.createCellStyle();
            //정렬
//            styleOfBoardMoneyFontBlack11.setAlignment(CellStyle.ALIGN_RIGHT); //우측 정렬
//            styleOfBoardMoneyFontBlack11.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //높이 가운데 정렬
            //테두리 선 (우,좌,위,아래)
//            styleOfBoardMoneyFontBlack11.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardMoneyFontBlack11.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardMoneyFontBlack11.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            styleOfBoardMoneyFontBlack11.setBorderBottom(HSSFCellStyle.BORDER_THIN);
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
                //sheet.setColumnWidth(i, headerWidths[i]);
            }


            for(Board board : list) {
                row = sheet.createRow(rowNo++);

                //System.out.println("map : " + map);

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
                        //System.out.println("type : " + type);
		        		/*
		        		switch(type){

			                case Cell.CELL_TYPE_BLANK:
			                    break;

			                case Cell.CELL_TYPE_NUMERIC:
			                    System.out.println(cell.getNumericCellValue() + "1");
			                	cell.setCellValue(cell.getNumericCellValue());
			                    break;

			                case Cell.CELL_TYPE_STRING:
			                    System.out.println(cell.getStringCellValue() + "2");
			                	cell.setCellValue(cell.getStringCellValue());
			                	System.out.println("2");
			                    break;

			                case Cell.CELL_TYPE_ERROR:
			                	System.out.println(cell.getErrorCellValue() + "3");
			                	cell.setCellErrorValue(cell.getErrorCellValue());
			                    break;

			                case Cell.CELL_TYPE_BOOLEAN:
			                	System.out.println(cell.getBooleanCellValue() + "4");
			                	cell.setCellValue( cell.getBooleanCellValue());
			                    break;

			                case Cell.CELL_TYPE_FORMULA:
			                	System.out.println(cell.getCellFormula() + "5");
			                    //System.out.print(cell.getCellFormula());
			                	cell.setCellFormula(cell.getCellFormula());
			                    break;
		                }
		                */
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
                //sheet.setColumnWidth(i, headerWidths[i]);
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
                        //System.out.println(headerEnNmArr[i] + ":" + map.get(headerEnNmArr[i]));

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

                        //System.out.println("styleMap.get(headerEnNm) : " + styleMap.get(headerEnNm));

                        cell = row.createCell(j);

                        //  금액 style
                        if("amt".equals(styleMap.get(headerEnNm))) {
//                            cell.setCellValue(StringUtil.getThousandCommaSeperator(inValue));
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
            log.error("authenticate RuntimeException"); //20191010 취약점  e.printStackTrace();
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

    // 엑셀 다운로드
//    public void excelSecretFileDownload(HttpServletResponse response, String fileName, String password) throws Exception {
//
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "ATTachment; Filename=" + URLEncoder.encode("P" + fileName+"_"+date.toString().substring(0,9), "UTF-8") + ".xlsx");
//
//        OutputStream fileOut = response.getOutputStream();
//        response.resetBuffer();
//        response.setBufferSize(1024 * 4);
//
////        InputStream filein = new InputStream(fileOut.toString());
//        OPCPackage opc = OPCPackage.create(fileOut);
//
//        // poi  엑셀 암호화
//        POIFSFileSystem fs = new POIFSFileSystem();
//        EncryptionInfo info = new EncryptionInfo((LittleEndianInput) fs, EncryptionMode.agile);
//        Encryptor enc = info.getEncryptor();
//        // 패스워드 입력
//        enc.confirmPassword(password);
//        OutputStream os = enc.getDataStream(fs);
//        opc.save(os);
//        opc.close();
//
//        fs.writeFilesystem(fileOut);
//
//        workbook.write(fileOut);
//        fileOut.close();
//    }


    // 엑셀 다운로드
//    public void excelSecretFileDownload2(HttpServletResponse response, String fileName, String password) throws Exception {
//
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "ATTachment; Filename=" + URLEncoder.encode("P" + fileName+"_"+date.toString().substring(0,9), "UTF-8") + ".xlsx");
//        response.resetBuffer();
//        response.setBufferSize(1024 * 4);
//
//
//        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
//        workbook.write(fileOut);
//
//        InputStream filein = new ByteArrayInputStream(fileOut.toByteArray());
//        POIFSFileSystem fs = new POIFSFileSystem();
//        EncryptionInfo info = new EncryptionInfo(fs, EncryptionMode.agile);
//        //EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
//        Encryptor enc = info.getEncryptor();
//
//        enc.confirmPassword(password);
//
//
//        OPCPackage opc = OPCPackage.open(filein);
//        OutputStream os = enc.getDataStream(fs);
//        opc.save(os);
//        opc.close();
//
//
//        OutputStream fileOut2 = null;
//        //out.clear();
//        //out = pageContext.pushBody();
//        fileOut2 = response.getOutputStream();
//        fs.writeFilesystem(fileOut2);
//        fileOut2.close();
//        fileOut.close();
//
//    }


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
