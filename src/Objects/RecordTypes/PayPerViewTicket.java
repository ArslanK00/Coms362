package Objects.RecordTypes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.ArrayList;

public class PayPerViewTicket extends AbstractTicket {

    public PayPerViewTicket(String name, float cost, YearMonth date, int amount) {
        super(name, cost, date, amount, RecordEnum.PayPerViewTicket);
    }

    public PayPerViewTicket(String name) {
        super(name);
    }

    public ArrayList<PayPerViewTicket> TurnAllPayPerViewToRecords(boolean sendToRecords) {
        PayPerViewController uploadPayPerView = new PayPerViewController(false);
        String[][] payPerViewData = uploadPayPerView.getPayPerView();

        ArrayList<PayPerViewTicket> payPerViewList = new ArrayList<>();

        for (int i = 0; i < payPerViewData.length; i++) {
            float tempCost = (Float.parseFloat(payPerViewData[i][3]) * Float.parseFloat(payPerViewData[i][4])) 
                           - (Float.parseFloat(payPerViewData[i][3]) * Float.parseFloat(payPerViewData[i][5]));
            int amount = Integer.parseInt(payPerViewData[i][4]);
            
            PayPerViewTicket payPerViewTicket = new PayPerViewTicket(payPerViewData[i][2].trim(), tempCost, YearMonth.parse(payPerViewData[i][6].trim()), amount);
            payPerViewList.add(payPerViewTicket);
            
            if (!sendToRecords) {
                System.out.println(payPerViewTicket.name + ", " + payPerViewTicket.cost + ", " + payPerViewTicket.date);
            }
        }
        
        if (sendToRecords) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FilePathToRecords))) {
                out.writeObject(payPerViewList);
                System.out.println("Successfully saved all pay-per-view ticket records");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return payPerViewList;
    }

    public ArrayList<PayPerViewTicket> readRecords() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FilePathToRecords))) {
            ArrayList<PayPerViewTicket> arr = (ArrayList<PayPerViewTicket>) in.readObject();
            for(PayPerViewTicket m : arr) {
                System.out.println("Record Type: " + RecType + " Name: " + m.name + " Cost: " + m.cost + " Date: " + m.date);
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}