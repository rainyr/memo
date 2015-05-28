package com.ruiy.service;

import com.ruiy.annotation.PermissionAnnotation;
import com.ruiy.annotation.UserAnnotation;
import com.ruiy.model.Document;
import com.ruiy.model.Permission;
import com.ruiy.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by ruiyang on 15-05-21.
 */

@Service
public class DocumentService {

    @PermissionAnnotation(value= Permission.READ)
    public String read(@UserAnnotation User user, Document document) {
        return document.getContent();
    }

    @PermissionAnnotation(value=Permission.EDIT)
    public void write(@UserAnnotation User user, Document document, String newContent) {
        document.setContent(newContent);
    }
}
