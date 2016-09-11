//package vn.phuong.tester.automation.service;
//
//import com.ekinoffy.tf.wording.convertor.ColumnConvertor;
//import com.ekinoffy.tf.wording.model.GoogleAcc;
//import com.ekinoffy.tf.wording.service.ExcelReaderHelper;
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.store.MemoryDataStoreFactory;
//import com.google.gdata.client.spreadsheet.CellQuery;
//import com.google.gdata.client.spreadsheet.SpreadsheetService;
//import com.google.gdata.client.spreadsheet.WorksheetQuery;
//import com.google.gdata.data.spreadsheet.CellEntry;
//import com.google.gdata.data.spreadsheet.CellFeed;
//import com.google.gdata.data.spreadsheet.WorksheetEntry;
//import com.google.gdata.data.spreadsheet.WorksheetFeed;
//import com.google.gdata.util.common.base.StringUtil;
//import org.apache.commons.collections.CollectionUtils;
//
//import javax.swing.*;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class GGDriveDataHelper implements ExcelReaderHelper {
//
//	private SpreadsheetService readService = new SpreadsheetService(
//			"Data Driven");
//	private JTextArea log;
//	private Iterator<CellEntry> infoCells;
//	private URL currentCellFeedUrl, infoCellFeedUrl;
//	private String url, spreadSheetKey;
//	private Map<Integer, List<CellEntry>> recordData = new HashMap<Integer, List<CellEntry>>();
//	private Iterator<CellEntry> indexCell;
//	private int currentIndex = 0, maxSize;
//
//	private URL spreadsheetFeedUrl;
//	private final static String CLIENT_ID = "496017760352-gg80kp3ii0ar1e8k1pg4h3c5v4fp8fl1.apps.googleusercontent.com";
//	private final static String CLIENT_SECRET = "k7wKYY0dmduftTfmtNpN7GP0";
//
//	private final static String REDIRECT_URI = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";
//
//
//	String[] SCOPESArray = {"https://spreadsheets.google.com/feeds", "https://spreadsheets.google.com/feeds/spreadsheets/private/full", "https://docs.google.com/feeds"};
//
//	private static final String INFO_SHEET_NAME = "info";
//	//URL format: https://docs.google.com/spreadsheet/ccc?key=[spreadsheet_key]
//	// https://spreadsheets.google.com/feeds/worksheets/key/private/full
//
//
//	public GGDriveDataHelper(GoogleAcc acc, JTextArea log){
//		this.log = log;
//		try{
//			readService.setOAuth2Credentials(authorize());
//			spreadSheetKey = acc.getStartUpSheet();
//			infoCellFeedUrl = getWorksheet(INFO_SHEET_NAME).getCellFeedUrl();
//			processInfoSheet();
//		}catch(Exception e){
//			log.append(e.getMessage() + StringUtil.LINE_BREAKS);
//		}
//	}
//
//	/** Authorizes the installed application to access user's protected data. */
//	private Credential authorize() throws Exception {
//		URL url1 = getClass().getClassLoader().getResource("client_secrets.json");
//		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(new JacksonFactory(),
//				new InputStreamReader(url1.openStream()));
//		// set up authorization code flow
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//				new NetHttpTransport(), new JacksonFactory(), clientSecrets,
//				Arrays.asList(SCOPESArray)).setDataStoreFactory(new MemoryDataStoreFactory())
//				.build();
//		// authorize
//		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//	}
//
//
//	private void processInfoSheet() {
//		CellQuery cellQuery = new CellQuery(infoCellFeedUrl);
//		cellQuery.setMinimumCol(1);
//		cellQuery.setMaximumCol(2);
//		cellQuery.setMinimumRow(2);
//		try {
//			CellFeed cellFeed = readService.query(cellQuery, CellFeed.class);
//			infoCells = cellFeed.getEntries().iterator();
//			log.append("########Reading ["+ INFO_SHEET_NAME +"] completed! ########" + StringUtil.LINE_BREAKS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.append("[ERROR] Can't process sheet" + INFO_SHEET_NAME + StringUtil.LINE_BREAKS);
//		}
//	}
//
//	private WorksheetEntry getWorksheet(String worksheet) throws Exception {
//		log.append("########Sheet:"+ worksheet + "########" + StringUtil.LINE_BREAKS);
//		WorksheetQuery worksheetQuery = new WorksheetQuery(new URL("https://spreadsheets.google.com/feeds/worksheets/"+ spreadSheetKey +"/private/basic"));
//		worksheetQuery.setTitleExact(true);
//		worksheetQuery.setTitleQuery(worksheet.replaceAll("(\\r\\n|\\n)", StringUtil.EMPTY_STRING));
//		WorksheetFeed worksheetFeed = readService.query(worksheetQuery, WorksheetFeed.class);
//		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
//		if (CollectionUtils.isEmpty(worksheets)) {
//			throw new Exception("No worksheets with that name in spreadhsheet ");
//		}
//
//		return worksheets.get(0);
//	}
//
////	private WorksheetEntry getWorksheet(String worksheet) throws Exception {
////		WorksheetQuery worksheetQuery = new WorksheetQuery(
////				spreadSheet.getWorksheetFeedUrl());
////		worksheetQuery.setTitleQuery(worksheet);
////		WorksheetFeed worksheetFeed = readService.query(worksheetQuery,
////				WorksheetFeed.class);
////		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
////		if (CollectionUtils.isEmpty(worksheets)) {
////			throw new Exception("No worksheets with that name in spreadhsheet "
////					+ spreadSheet.getTitle().getPlainText());
////		}
////
////		return worksheets.get(0);
////	}
//
//
//
////	private SpreadsheetEntry getSpreadsheet(String spreadsheet)
////			throws Exception {
////		String spreadSheetKey = RegExrHelper.getSpreadSheetKey(spreadsheet);
////		SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(new URL("https://spreadsheets.google.com/feeds/spreadsheets/"+ spreadSheetKey +"/private/basic"));
//////		SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(FeedURLFactory
//////				.getDefault().getSpreadsheetsFeedUrl());
//////		spreadsheetQuery.setTitleQuery(spreadsheet);
////		SpreadsheetFeed spreadsheetFeed = readService.query(spreadsheetQuery,
////				SpreadsheetFeed.class);
////		List<SpreadsheetEntry> spreadsheets = spreadsheetFeed.getEntries();
////		if (spreadsheets.isEmpty()) {
////			throw new Exception("No spreadsheets with that name");
////		}
////		return spreadsheets.get(0);
////	}
//
//	private CellEntry getCell(int col, int row) throws Exception {
//		return getCells(col + 1, col + 1, row + 1, row + 1).get(0);
//	}
//
//	private List<CellEntry> getCells(int minCol, int maxCol, int minRow,
//			int maxRow) throws Exception {
//		CellQuery cellQuery = new CellQuery(currentCellFeedUrl);
//		cellQuery.setMinimumCol(minCol);
//		cellQuery.setMaximumCol(maxCol);
//		cellQuery.setMinimumRow(minRow);
//		cellQuery.setMaximumRow(maxRow);
//		return getCells(cellQuery);
//	}
//
//	private List<CellEntry> getCells(CellQuery cellQuery) throws Exception {
//		CellFeed cellFeed = readService.query(cellQuery, CellFeed.class);
//		return cellFeed.getEntries();
//	}
//
//	public String getCellData(int rowNum, int colNum) {
//		CellEntry data;
//		try {
//			data = getCell(colNum, rowNum);
//			return data.getPlainTextContent();
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.append("[ERROR] Can't get value from cell["+ ColumnConvertor.toName(colNum + 1)+"]["+ rowNum + 1+"]"+ StringUtil.LINE_BREAKS);
//			return StringUtil.EMPTY_STRING;
//		}
//	}
//
//	public void setCellData(String result, int rowNum, int colNum)
//			throws IOException {
//		CellEntry newEntry = new CellEntry(rowNum + 1, colNum + 1, result);
//		try {
//			readService.insert(currentCellFeedUrl, newEntry);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.append("[ERROR] Can't insert value ["+ result +"] into cell["+ ColumnConvertor.toName(colNum + 1)+"]["+ rowNum + 1+"]"+ StringUtil.LINE_BREAKS);
//		}
//	}
//
//	private CellQuery getAllColumnRecord(int indexCol) {
//		CellQuery query = new CellQuery(currentCellFeedUrl);
//		query.setMinimumCol(indexCol);
//		query.setMaximumCol(indexCol);
//		query.setMinimumRow(2);// except the column title
//		query.setReturnEmpty(true);
//		return query;
//	}
//
//	public int getCurrentIndex() {
//		return currentIndex;
//	}
//
//	public String getCurrentCellData(int indexCol) {
//		int indexColumn = indexCol + 1;
//		List<CellEntry> cellValue;
//		if (!recordData.containsKey(indexColumn)) {
//			try {
//				CellQuery query = getAllColumnRecord(indexColumn);
////				query.setMaxResults(2+maxSize-1);
//				recordData.put(indexColumn, getCells(query));
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//		cellValue = recordData.get(indexColumn);
//		if(cellValue.size() > currentIndex -1){
//			return cellValue.get(currentIndex -1).getPlainTextContent();
//		}
//		return null;
//	}
//
//	public void setCurrentCellData(int colNum, String result){
//		if(colNum <0 ) return;
//		try{
//			CellEntry newEntry = new CellEntry(currentIndex+1, colNum+1, result);
//			readService.insert(currentCellFeedUrl, newEntry);
//		}catch(Exception e){
//			log.append("[ERROR- GData] Can't insert value ["+ result +"] into cell["+ ColumnConvertor.toName(colNum + 1)+"]["+ currentIndex+1 +"]"+ StringUtil.LINE_BREAKS);
//		}
//
//	}
//
//	public boolean getSheet() {
//			if (infoCells.hasNext()) {
//			recordData.clear();
//			indexCell = null;
//			CellEntry nextWorksheet = infoCells.next();
//			try {
//				currentCellFeedUrl = getWorksheet(nextWorksheet.getPlainTextContent()).getCellFeedUrl();
//				this.url = infoCells.next().getPlainTextContent();
//				return true;
//			} catch (Exception e) {
//				// read next sheet
//				e.printStackTrace();
//				return true;
//			}
//		} else {
//			return false;
//		}
//	}
//
//	public boolean nextRecord(int indexCol) {
//		int indexColumn = indexCol + 1;
//		try{
//			if (indexCell == null) {
//				List<CellEntry> listCell = getCells(getAllColumnRecord(indexColumn));
//				maxSize = listCell.size();
//				indexCell =  listCell.iterator();
//			}
//			if (indexCell.hasNext()) {
//				currentIndex = Integer.parseInt(indexCell.next().getPlainTextContent());
//				log.append("[Info] Row No:" + currentIndex + StringUtil.LINE_BREAKS);
//				return true;
//			}
//		}catch (Exception e) {
//			log.append("[Error] Index column is invalid!!" + StringUtil.LINE_BREAKS);
//			log.append("##########End Sheet##########" + StringUtil.LINE_BREAKS);
//			e.printStackTrace();
//			return false;
//		}
//		log.append("[Info] No more row in sheet" + StringUtil.LINE_BREAKS);
//		log.append("##########End Sheet##########" + StringUtil.LINE_BREAKS);
//		return false;
//	}
//
//	public String getUrl() {
//		return this.url;
//	}
//}
