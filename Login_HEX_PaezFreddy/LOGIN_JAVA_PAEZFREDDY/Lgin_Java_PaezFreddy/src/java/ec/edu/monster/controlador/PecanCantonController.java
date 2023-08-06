package ec.edu.monster.controlador;

import ec.edu.monster.modelo.PecanCanton;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.modelo.PecanCantonFacade;

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

@Named("pecanCantonController")
@SessionScoped
public class PecanCantonController implements Serializable {

    @EJB
    private ec.edu.monster.modelo.PecanCantonFacade ejbFacade;
    private List<PecanCanton> items = null;
    private PecanCanton selected;

    public PecanCantonController() {
    }

    public PecanCanton getSelected() {
        return selected;
    }

    public void setSelected(PecanCanton selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPecanCantonPK().setPepaiCodigo(selected.getPeproProvin().getPeproProvinPK().getPepaiCodigo());
        selected.getPecanCantonPK().setPeproCodigo(selected.getPeproProvin().getPeproProvinPK().getPeproCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setPecanCantonPK(new ec.edu.monster.modelo.PecanCantonPK());
    }

    private PecanCantonFacade getFacade() {
        return ejbFacade;
    }

    public PecanCanton prepareCreate() {
        selected = new PecanCanton();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PecanCantonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PecanCantonUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PecanCantonDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PecanCanton> getItems() {
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

    public PecanCanton getPecanCanton(ec.edu.monster.modelo.PecanCantonPK id) {
        return getFacade().find(id);
    }

    public List<PecanCanton> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PecanCanton> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PecanCanton.class)
    public static class PecanCantonControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PecanCantonController controller = (PecanCantonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pecanCantonController");
            return controller.getPecanCanton(getKey(value));
        }

        ec.edu.monster.modelo.PecanCantonPK getKey(String value) {
            ec.edu.monster.modelo.PecanCantonPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.modelo.PecanCantonPK();
            key.setPepaiCodigo(values[0]);
            key.setPeproCodigo(values[1]);
            key.setPecanCodigo(values[2]);
            return key;
        }

        String getStringKey(ec.edu.monster.modelo.PecanCantonPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPepaiCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeproCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPecanCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PecanCanton) {
                PecanCanton o = (PecanCanton) object;
                return getStringKey(o.getPecanCantonPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PecanCanton.class.getName()});
                return null;
            }
        }

    }

}
