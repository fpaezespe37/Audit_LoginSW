package ec.edu.monster.controlador;

import ec.edu.monster.modelo.XeopcOpcio;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.controlador.util.PaginationHelper;
import ec.edu.monster.modelo.XeopcOpcioFacade;

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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("xeopcOpcioController")
@SessionScoped
public class XeopcOpcioController implements Serializable {

    private XeopcOpcio current;
    private DataModel items = null;
    @EJB
    private ec.edu.monster.modelo.XeopcOpcioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private XeopcOpcio selected;

    public XeopcOpcioController() {
    }

    public XeopcOpcio getSelected() {
        if (current == null) {
            current = new XeopcOpcio();
            current.setXeopcOpcioPK(new ec.edu.monster.modelo.XeopcOpcioPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(XeopcOpcio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getXeopcOpcioPK().setXevenCodigo(selected.getXesisVentan().getXevenCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setXeopcOpcioPK(new ec.edu.monster.modelo.XeopcOpcioPK());
    }

    private XeopcOpcioFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(50) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (XeopcOpcio) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new XeopcOpcio();
        current.setXeopcOpcioPK(new ec.edu.monster.modelo.XeopcOpcioPK());
        selectedItemIndex = -1;
        return "List";
    }

    public String create() {
        try {
            current.getXeopcOpcioPK().setXevenCodigo(current.getXesisVentan().getXevenCodigo());
            getFacade().create(current);
            JsfUtil.addSuccessMessage("Opción creada exitosamente");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (XeopcOpcio) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Opción editada exitosamente");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (XeopcOpcio) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("Opción eliminada exitosamente");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
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
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public XeopcOpcio getXeopcOpcio(ec.edu.monster.modelo.XeopcOpcioPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = XeopcOpcio.class)
    public static class XeopcOpcioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XeopcOpcioController controller = (XeopcOpcioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xeopcOpcioController");
            return controller.getXeopcOpcio(getKey(value));
        }

        ec.edu.monster.modelo.XeopcOpcioPK getKey(String value) {
            ec.edu.monster.modelo.XeopcOpcioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.modelo.XeopcOpcioPK();
            key.setXevenCodigo(values[0]);
            key.setXeopcCodigo(values[1]);
            return key;
        }

        String getStringKey(ec.edu.monster.modelo.XeopcOpcioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getXevenCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeopcCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof XeopcOpcio) {
                XeopcOpcio o = (XeopcOpcio) object;
                return getStringKey(o.getXeopcOpcioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XeopcOpcio.class.getName()});
                return null;
            }
        }

    }

}
