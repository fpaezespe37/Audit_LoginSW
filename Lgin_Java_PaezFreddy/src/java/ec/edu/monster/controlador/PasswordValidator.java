/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package ec.edu.monster.controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@FacesValidator("ContraseniaValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent uIComponent, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile("^(?=\\S*?[A-Z])(?=\\S*?\\d)\\S{8,16}$");
        Matcher matcher = pattern.matcher((CharSequence) value);
        HtmlInputText htmlInputText;
        htmlInputText = (HtmlInputText) uIComponent;
        String label;

        if (htmlInputText.getLabel() == null || htmlInputText.getLabel().trim().equals("")) {
            label = htmlInputText.getId();
        } else {
            label = htmlInputText.getLabel();
        }
        if (!matcher.matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL,"Contraseña inválida","\"La contraseña debe tener 8 a 16 caracteres y contener mínimo una mayúscula y un número."));
        }

    }

}
