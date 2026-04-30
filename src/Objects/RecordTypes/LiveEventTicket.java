package Objects.RecordTypes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.ArrayList;

public class LiveEventTicket extends AbstractTicket {

    public LiveEventTicket(String name, float cost, YearMonth date, int amount) {
        super(name, cost, date, amount, RecordEnum.LiveEventTicket);
    }

    public LiveEventTicket(String name) {
        super(name);
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }
        public RecordEnum getRecordEnum()
    {
        return RecType;
    }

    public ArrayList<LiveEventTicket> TurnAllLiveEventToRecords(boolean sendToRecords) {
        LiveEventController uploadLiveEvent = new LiveEventController(false);
        String[][] liveEventData = uploadLiveEvent.getLiveEvent();

        ArrayList<LiveEventTicket> liveEventList = new ArrayList<>();

        for (int i = 0; i < liveEventData.length; i++) {
            float tempCost = (Float.parseFloat(liveEventData[i][3]) * Float.parseFloat(liveEventData[i][4]))
                    - (Float.parseFloat(liveEventData[i][3]) * Float.parseFloat(liveEventData[i][5]));
            int amount = Integer.parseInt(liveEventData[i][4]);

            LiveEventTicket liveEventTicket = new LiveEventTicket(
                    liveEventData[i][2].trim(),
                    tempCost,
                    YearMonth.parse(liveEventData[i][6].trim()),
                    amount);

            liveEventList.add(liveEventTicket);

            if (!sendToRecords) {
                System.out.println(liveEventTicket.name + ", " + liveEventTicket.cost + ", " + liveEventTicket.date);
            }
        }

        if (sendToRecords) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FilePathToRecords))) {
                out.writeObject(liveEventList);
                System.out.println("Successfully saved all live event ticket records");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return liveEventList;
    }

    public ArrayList<LiveEventTicket> readRecords() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FilePathToRecords))) {
            ArrayList<LiveEventTicket> arr = (ArrayList<LiveEventTicket>) in.readObject();

            for (LiveEventTicket m : arr) {
                System.out.println("Record Type: " + RecType + " Name: " + m.name + " Cost: " + m.cost + " Date: " + m.date);
            }

            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        String summary = "Record Type: " + RecType + "\n"
                + "Name: " + getName() + "\n"
                + "Cost: " + cost + "\n"
                + "Date: " + date + "\n"
                + "Amount: " + amount + "\n";

        if (description == null || description.length() == 0) {
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }
}