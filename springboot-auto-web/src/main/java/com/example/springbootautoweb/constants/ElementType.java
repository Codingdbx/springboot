package com.example.springbootautoweb.constants;


/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/11 17:50
 * @since JDK1.8
 */
public interface ElementType {

    String P = "p";

    String DIV = "div";

    String INPUT = "input";

    String HIDDEN_INPUT = "hidden_input";

    interface LocationType {

        String ID = "id";

        String XPATH = "xpath";

        String CLASS_NAME = "className";

        String TAG_NAME = "tagame";

        String CSS = "css";

        String NAME = "name";

        String LINK_TEXT = "linkText";

        String PARTIAL_LINK_TEXT = "partialLinkText";
    }
}
