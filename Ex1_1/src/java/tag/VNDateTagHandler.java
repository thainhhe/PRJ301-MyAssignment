/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class VNDateTagHandler extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        SimpleDateFormat sdf = new SimpleDateFormat("'Ngày' dd 'tháng' MM 'năm' yyyy");
        String formattedDate = sdf.format(new Date());

        try {
            out.write(formattedDate);
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
