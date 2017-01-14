package adminapp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import serializable.model.Competition;
import serializable.model.Person;
import serializable.model.Serializator;
import serializable.model.Tournament;

/**
 *
 * @author Jacek
 */
public class DataRegistration {

    private static final String PATH = "src/adminapp/model/data/";
    private static final String TEMPLATE = "template_blocked.xls";

    public static void getData(Tournament t, Stage stage) {
        File fileOpen = null;
        InputStream fileIn = null;
        Workbook wb = null;
        
        try {
        //--Open--
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Skoroszyt programu Excel", "*.xls","*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            
            //Show open file dialog
            fileOpen = fileChooser.showOpenDialog(stage);

            
            if (fileOpen != null) {
                System.out.println("file not null!");
                
                fileIn = new FileInputStream(fileOpen);
                if(fileIn==null)
                    System.out.println("null fileIn");
                else System.out.println("full fileIn");
                //fileOut = new FileOutputStream(new File(PATH + formName));
                
                wb = WorkbookFactory.create(fileIn);
                            if (wb instanceof HSSFWorkbook) {
    // do whatever
    System.out.println("hssf!");
} else if (wb instanceof SXSSFWorkbook) {
    // do whatever
    System.out.println("sxssf!");
} else if (wb instanceof XSSFWorkbook) {
    // do whatever
    System.out.println("xssf!");
}

               
                if(wb==null)
                    System.out.println("null wb");
                else System.out.println("full wb");
                
                
            //read first sheet
            Sheet titleSheet = wb.getSheetAt(0);
            Row r;

            String club;
            r = titleSheet.getRow(6);
            club = r.getCell(3).getStringCellValue();

            String coach;
            r = titleSheet.getRow(8);
            coach = r.getCell(3).getStringCellValue();

            System.out.println("club: " + club);
            System.out.println("coach: " + coach);
            

            //read list 1
            for(int j=1;j<=t.getCompetitions().size();j++){
                Sheet sheet = wb.getSheetAt(j);
                ArrayList<Person> people = new ArrayList<>();
                for (int i = 5; i <= sheet.getLastRowNum(); i++) {
                    r = sheet.getRow(i);
                    if (r.getCell(1).getStringCellValue().equals("")) {
                        break;
                    }

                    String name = r.getCell(1).getStringCellValue();
                    String surname = r.getCell(2).getStringCellValue();
                    Integer year = (int) r.getCell(3).getNumericCellValue();
                    String rank = r.getCell(4).getStringCellValue();
                    System.out.println(name + " " + surname + " (" + year + ") " + rank);
                    
                    people.add(new Person(name, surname, year, rank, club));
                    //boolean add = t.getCompetitions().get(j-1).addPlayer(new Person(name, surname, year, rank, club));
                   // System.out.println(name+" "+surname+" added to the list "+j);
                }
                
                t.getCompetitions().get(j-1).initProperties();
                t.getCompetitions().get(j-1).addPlayers(people);
                System.out.println("Contestants added to the list "+j);
            }
                
                
                
                
                
                
                System.out.println("Form imported saved");
            }else System.out.println("null file!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (Throwable e) {
    System.out.println(e);
    System.out.println(e.getMessage());

}finally {
            try {
                fileIn.close();
                wb.close();
            } catch (IOException ex) {
                Logger.getLogger(DataRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }

    public static void generateForm(Tournament t, String formName, Stage stage) {
        FileInputStream file = null;
        FileOutputStream fileOut = null;
        File fileSave = null;
        HSSFWorkbook wb = null;

        //String formName = "formularzZgl2.xls";
        String title = t.getTitle();
        String date = t.getDate().toString();
        String place = t.getPlace();

        ArrayList<Competition> lists = t.getCompetitions();

        try {
            file = new FileInputStream(new File(PATH + TEMPLATE));
            wb = new HSSFWorkbook(file);

            //fill in first sheet
            HSSFSheet titleSheet = wb.getSheetAt(0);
            Row r;

            r = titleSheet.getRow(1);
            r.getCell(1).setCellValue(title);

            r = titleSheet.getRow(15);
            r.getCell(3).setCellValue(date);
            r = titleSheet.getRow(16);
            r.getCell(3).setCellValue(place);

            int row = 21;
            for (Competition c : lists) {
                r = titleSheet.getRow(row++);
                r.getCell(1).setCellValue(c.getID());
                r.getCell(3).setCellValue(c.getTitle());
            }

            //create lists
            for (int i = 1; i < lists.size(); i++) {
                wb.cloneSheet(1);
            }
            for (int i = 1; i <= lists.size(); i++) {
                wb.setSheetName(i, "Lista nr " + lists.get(i - 1).getID());

                Sheet listSheet = wb.getSheetAt(i);
                r = listSheet.getRow(1);
                r.getCell(1).setCellValue("Lista nr " + lists.get(i - 1).getID() + ". " + lists.get(i - 1).getTitle());
                r = listSheet.getRow(2);
                r.getCell(1).setCellValue(lists.get(i - 1).getDescr());
            }

            //--Save--
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Skoroszyt programu Excel", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(formName + ".xls");

            //Show save file dialog
            fileSave = fileChooser.showSaveDialog(stage);
            stage.show();
            if (fileSave != null) {
                fileOut = new FileOutputStream(fileSave);
                //fileOut = new FileOutputStream(new File(PATH + formName));
                wb.write(fileOut);
                System.out.println("Form succesfully saved");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
                fileOut.close();
                wb.close();
            } catch (IOException ex) {
                Logger.getLogger(DataRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
