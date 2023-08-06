package ec.edu.monster.controlador;

import ec.edu.monster.modelo.XeuxpUsupe;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.controlador.util.PaginationHelper;
import ec.edu.monster.modelo.XeusuUsuar;
import ec.edu.monster.modelo.XeuxpUsupeFacade;

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

@Named("xeuxpUsupeController")
@SessionScoped
public class XeuxpUsupeController implements Serializable {
    
    private XeuxpUsupe current;
    private DataModel items = null;
    @EJB
    private ec.edu.monster.modelo.XeuxpUsupeFacade ejbFacade;
    private XeuxpUsupe selected;
    private String perfil = "";
    private XeusuUsuar codUser = new XeusuUsuar();
    private XeusuUsuar codUserDel = new XeusuUsuar();
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public XeuxpUsupeController() {
    }

    public XeuxpUsupe getSelected() {
        return selected;
    }

    public void setSelected(XeuxpUsupe selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getXeuxpUsupePK().setXeperCodigo(selected.getXeperPerfi().getXeperCodigo());
        selected.getXeuxpUsupePK().setXeusuCodigo(selected.getXeusuUsuar().getXeusuCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setXeuxpUsupePK(new ec.edu.monster.modelo.XeuxpUsupePK());
    }

    private XeuxpUsupeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

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
        current = (XeuxpUsupe) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new XeuxpUsupe();
        current.setXeuxpUsupePK(new ec.edu.monster.modelo.XeuxpUsupePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getXeuxpUsupePK().setXeusuCodigo(current.getXeusuUsuar().getXeusuCodigo());
            current.getXeuxpUsupePK().setXeperCodigo(current.getXeperPerfi().getXeperCodigo());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("XeuxpUsupeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (XeuxpUsupe) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    public void addOne() {
        // System.out.println(usuario);
        getFacade().insertWithQuery(codUser.getXeusuCodigo(),perfil, codUser.getPeempEmple().getPeempEmail());
    }
    
    public void addAll(List<XeusuUsuar> users) {
        for (int i = 0; i < users.size(); i++) {
 
            getFacade().insertWithQuery(users.get(i).getXeusuCodigo(),perfil,users.get(i).getPeempEmple().getPeempEmail());
        }
 
    }
    
    public void deleteAll() {
 
        List<XeuxpUsupe> users = getFacade().getUsuarioPerfilP(perfil);
        for (int i = 0; i < users.size(); i++) {
 
            getFacade().remove(users.get(i));
        }
 
    }
 
    public void deleteByCodPas() {
 
        getFacade().removeAssignation(codUserDel.getXeusuCodigo());
    }
    
    public List<XeusuUsuar> getUsuarios(String id) {
//        System.out.println( "usuario" +getFacade().findUsuarios(id).get(0).getXeusuUsuarPK().getXeusuEmail());
        String[] splitId=id.split("-");
        if ((getFacade().findUsuarios(splitId[0]).size()>0)) {
            getFacade().findUsuarios(splitId[0]).get(0).getPeempEmple().getPeempEmail();
        }
        return getFacade().findUsuarios(splitId[0]);
    }

    public String update() {
        try {
            current.getXeuxpUsupePK().setXeusuCodigo(current.getXeusuUsuar().getXeusuCodigo());
            current.getXeuxpUsupePK().setXeperCodigo(current.getXeperPerfi().getXeperCodigo());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("XeuxpUsupeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (XeuxpUsupe) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("XeuxpUsupeDeleted"));
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

    public XeuxpUsupe getXeuxpUsupe(ec.edu.monster.modelo.XeuxpUsupePK id) {
        return getFacade().find(id);
    }

    public List<XeuxpUsupe> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<XeuxpUsupe> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public String getPerfil() {
        return perfil;
    }
 
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    public XeusuUsuar getCodUser() {
        return codUser;
    }
 
    public void setCodUser(XeusuUsuar codUser) {
        this.codUser = codUser;
    }
 
    public XeusuUsuar getCodUserDel() {
        return codUserDel;
    }
 
    public void setCodUserDel(XeusuUsuar codUserDel) {
        this.codUserDel = codUserDel;
    }

    @FacesConverter(forClass = XeuxpUsupe.class)
    public static class XeuxpUsupeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XeuxpUsupeController controller = (XeuxpUsupeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xeuxpUsupeController");
            return controller.getXeuxpUsupe(getKey(value));
        }

        ec.edu.monster.modelo.XeuxpUsupePK getKey(String value) {
            ec.edu.monster.modelo.XeuxpUsupePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.modelo.XeuxpUsupePK();
            key.setXeusuCodigo(values[0]);
            key.setXeperCodigo(values[1]);
            key.setXeuxpCodusu(values[2]);
            return key;
        }

        String getStringKey(ec.edu.monster.modelo.XeuxpUsupePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getXeusuCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeperCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeuxpCodusu());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof XeuxpUsupe) {
                XeuxpUsupe o = (XeuxpUsupe) object;
                return getStringKey(o.getXeuxpUsupePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XeuxpUsupe.class.getName()});
                return null;
            }
        }

    }

}
