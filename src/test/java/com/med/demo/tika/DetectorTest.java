package com.med.demo.tika;

import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class DetectorTest {
    @Test
    public void dT() throws IOException {
        System.out.println(System.getProperty("user.dir"));
//        String fileType = new Tika().detect(new File("src/test/java/com/med/demo/tika/test.png"));
        String fileType = new Tika().detect(new FileInputStream(new File("src/test/java/com/med/demo/tika/New Microsoft PowerPoint Presentation.pptx")));
//        String fileType = new Tika().detect(new File("src/test/java/com/med/demo/tika/tika.tar"));
        System.out.println(fileType);
    }

    @Test
    public void dTForOLE2() throws Exception {
        Path path = Paths.get("src/test/java/com/med/demo/tika/New Microsoft PowerPoint Presentation.pptx");
        TikaInputStream inputStream = TikaInputStream.get(path);
        Detector detector = new DefaultDetector();
//        Metadata metadata = dTGetMetadata(new File(path.toUri()));
        Metadata metadata = new Metadata();
        MediaType mediaType = detector.detect(inputStream, metadata);
        System.out.println(mediaType.getType());
    }

    @Test
    public Metadata dTGetMetadata(File file) throws Exception {
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();   //empty metadata object
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();
        parser.parse(inputstream, handler, metadata, context);
// now this metadata object contains the extracted metadata of the given file.
        return metadata;
    }
}
