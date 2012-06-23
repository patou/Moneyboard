package com.sfeir.common.gwt.sample.moneyboard.server.file;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.fileupload.FileItem;
public class AppEngineFileItem implements FileItem {

        private static final long serialVersionUID = -1858970407232356116L;
        
        private ByteArrayOutputStream outputStream;
        
        private boolean isFormField;
        private String fieldName;
        private String name;
        private long size;
        private String contentType;
        
        public AppEngineFileItem(String fieldName, String contentType,
                        boolean isFormField, String fileName, int sizeThreshold) {
                outputStream = new ByteArrayOutputStream();
                this.fieldName = fieldName;
                this.name = fileName;
                this.size = sizeThreshold;
                this.contentType = contentType;
                this.isFormField = isFormField;
        }
        
        @Override
        public void delete() {
                //Nothing to do
        }

        @Override
        public byte[] get() {
                return outputStream.toByteArray();
        }

        @Override
        public String getContentType() {
                return contentType;
        }

        @Override
        public String getFieldName() {
                return fieldName;
        }

        @Override
        public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(outputStream.toByteArray());
        }

        @Override
        public String getName() {
                return name;
        }

        @Override
        public long getSize() {
                return size;
        }

        @Override
        public String getString() {
                return new String(outputStream.toByteArray());
        }

        @Override
        public String getString(String s) throws UnsupportedEncodingException {
        return new String(outputStream.toByteArray(), s);
        }

        @Override
        public boolean isFormField() {
                return isFormField;
        }

        @Override
        public boolean isInMemory() {
                return true;
        }

        @Override
        public void setFieldName(String s) {
                this.fieldName = s;
        }

        @Override
        public void setFormField(boolean flag) {
                this.isFormField = flag;
        }

        @Override
        public void write(File file) throws Exception {}

        @Override
        public OutputStream getOutputStream() throws IOException {
                return this.outputStream;
        }
}