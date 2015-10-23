package others.java.leetcode;

import java.io.*;

public class ScriptConvertDate {
    public static void main(String [] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            FileReader in = new FileReader("/Users/khuang/Downloads/khuang_201510_ReviewRatingData_2.csv");
            br = new BufferedReader(in);


            File file = new File("/Users/khuang/Downloads/khuang_201510_ReviewRatingData2.csv");
            if (!file.exists())
                file.createNewFile();
            FileWriter out = new FileWriter(file);
            bw = new BufferedWriter(out);

            String line;
            String lastline = null;
            int count = 1;
            int countt = 0;
            while ((line = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder(line);


                if (line.equals(lastline))
                    continue;
                lastline = line;

                String[] test = sb.toString().split(",");
                if (count > 1 && test.length < 2)
                    continue;
                else if (count > 1 && test[1].length() != 22)
                    continue;
                if (count>1 && test.length  >= 4 && test[3].indexOf('.') > 0)
                    countt++;

                count++;
                sb.insert(4, '-');
                sb.insert(7, "-01");
                line = sb.toString();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("==="+countt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
