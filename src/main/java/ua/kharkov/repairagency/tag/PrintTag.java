package ua.kharkov.repairagency.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class PrintTag extends SimpleTagSupport {
    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }
    StringWriter sw = new StringWriter();
    public void doTag() throws JspException, IOException {
        if (message != null) {
            JspWriter out = getJspContext().getOut();
            out.println( message );
        } else {
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
}