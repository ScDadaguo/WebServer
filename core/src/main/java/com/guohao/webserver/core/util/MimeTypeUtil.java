package com.guohao.webserver.core.util;

import eu.medsea.mimeutil.MimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

import static com.guohao.webserver.core.constant.ContextConstant.DEFAULT_CONTENT_TYPE;

/**
 * @FileName: MimeTypeUtil.java
 * @Description: MimeTypeUtil.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 14:34
 */
@Slf4j
public class MimeTypeUtil {
    static {
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
    }

    public static String getTypes(String fileName) {
        if(fileName.endsWith(".html")){
            return DEFAULT_CONTENT_TYPE;
        }
        Collection mimeTypes = MimeUtil.getMimeTypes(MimeTypeUtil.class.getResource(fileName));
        return mimeTypes.toArray()[0].toString();
    }

}
