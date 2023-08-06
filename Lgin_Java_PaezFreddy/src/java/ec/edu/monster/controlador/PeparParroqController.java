package ec.edu.monster.controlador;

import ec.edu.monster.modelo.PeparParroq;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.modelo.PeparParroqFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("peparParroqController")
@SessionScoped
public class PeparParroqController implements Serializable {

    @EJB
    private ec.edu.monster.modelo.PeparParroqFacade ejbFacade;
    private List<PeparParroq> items = null;
    private PeparParroq selected;

    public PeparParroqController() {
    }

    public PeparParroq getSelected() {
        return selected;
    }

    public void setSelected(PeparParroq selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPeparParroqPK().setPecanCodigo(selected.getPecanCanton().getPecanCantonPK().getPecanCodigo());
        selected.getPeparParroqPK().setPepaiCodigo(selected.getPecanCanton().getPecanCantonPK().getPepaiCodigo());
        selected.getPeparParroqPK().setPeproCodigo(selected.getPecanCanton().getPecanCantonPK().getPeproCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setPeparParroqPK(new ec.edu.monster.modelo.PeparParroqPK());
    }

    private PeparParroqFacade getFacade() {
        return ejbFacade;
    }

    public PeparParroq prepareCreate() {
        selected = new PeparParroq();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeparParroqCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeparParroqUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeparParroqDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PeparParroq> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PeparParroq getPeparParroq(ec.edu.monster.modelo.PeparParroqPK id) {
        return getFacade().find(id);
    }

    public List<PeparParroq> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeparParroq> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeparParroq.class)
    public static class PeparParroqControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeparParroqController controller = (PeparParroqController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peparParroqController");
            return controller.getPeparParroq(getKey(value));
        }

        ec.edu.monster.modelo.PeparParroqPK getKey(String value) {
            ec.edu.monster.modelo.PeparParroqPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.modelo.PeparParroqPK();
            key.setPepaiCodigo(values[0]);
            key.setPeproCodigo(values[1]);
            key.setPecanCodigo(values[2]);
            key.setPeparCodigo(values[3]);
            return key;
        }

        String getStringKey(ec.edu.monster.modelo.PeparParroqPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPepaiCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeproCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPecanCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeparCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeparParroq) {
                PeparParroq o = (PeparParroq) object;
                return getStringKey(o.getPeparParroqPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeparParroq.class.getName()});
                return null;
            }
        }

    }

}
