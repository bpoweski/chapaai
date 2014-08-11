package com.derbysoft.chapaai.adapter.pusher.integrationtest;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class XMLUtils {

    public final static String URL_ENCODING = "UTF-8";
    public final static String STRING_ENCODING = "UTF8";

    public static String marshall(Object obj) {
        try {
            IBindingFactory jc = BindingDirectory.getFactory(obj.getClass());
            IMarshallingContext marshaller = jc.createMarshallingContext();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            marshaller.marshalDocument(obj, URL_ENCODING, null, out);
            return out.toString(STRING_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JiBXException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String readXML(String filePath) {
        StringBuffer buffer = new StringBuffer();
        InputStream is = null;

        BufferedReader reader = null;
        try {
            is = new FileInputStream(XMLUtils.class.getResource("/").getFile()+filePath);
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            line = reader.readLine();
            while (line != null) {
                buffer.append(line.trim());
                line = reader.readLine();
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

}
