package com.sfeir.common.gwt.sample.moneyboard.server.file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class AppEngineFileItemFactory extends DiskFileItemFactory{
        @Override
        public FileItem createItem(String fieldName, String contentType,
                        boolean isFormField, String fileName) {
                AppEngineFileItem result = new AppEngineFileItem(fieldName, contentType,
                                isFormField, fileName, getSizeThreshold());
                return result;
        }
}