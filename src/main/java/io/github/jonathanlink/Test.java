package io.github.jonathanlink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;

public class Test {

    public static void main(String[] args) {
        testLayoutStripper("forwardingOrder");
        testLayoutStripper("setlist");
        testLayoutStripper("invoice");        
    }

    private static void testLayoutStripper(String filename) {
        String string = null;
        try {
            String pdfFilepath = "./samples/" + filename + ".pdf";
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File(pdfFilepath), "r"));
            pdfParser.parse();
            PDDocument pdfDocument = new PDDocument(pdfParser.getDocument());
            PDFLayoutTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            string = pdfTextStripper.getText(pdfDocument);

            String outputFilepath = "./samples/" + filename + ".txt";
            FileOutputStream outputStream = new FileOutputStream(outputFilepath);
            byte[] strToBytes = string.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
