/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.modelo.XeopcOpcio;
import ec.edu.monster.modelo.XeoxpOpcpe;
import ec.edu.monster.modelo.XeoxpOpcpePK;
import ec.edu.monster.modelo.XeperPerfi;
import ec.edu.monster.modelo.XesisSiste;
import ec.edu.monster.modelo.XesisVentan;
import ec.edu.monster.modelo.XesisSisteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named(value = "asignacionOpcionesController")
@SessionScoped
public class AsignacionOpcionesController implements Serializable {

    private TreeNode root;
    private Object[] opcionSeleccionada;
    private String perfil;
    private List<List<XeopcOpcio>> opcionesdisp = new ArrayList();
    private List<XeoxpOpcpe> opcionesPorPerfil = new ArrayList();

    private int totOpciones = 0;
    private boolean updating = false;
    //////////
    private List<XesisSiste> sistemas;
    private List<XesisVentan> ventanas;
    private List<XeopcOpcio> opciones;
    HashMap<Integer, Object> option_values = new HashMap();
    boolean canReassign = true;

    @EJB
    private ec.edu.monster.modelo.XesisSisteFacade sistemasFacade;
    @EJB
    private ec.edu.monster.modelo.XesisVentanFacade ventanaFacade;
    @EJB
    private ec.edu.monster.modelo.XeopcOpcioFacade opcionFacade;
    @EJB
    private ec.edu.monster.modelo.XeoxpOpcpeFacade opcionperfilFacade;

    public AsignacionOpcionesController() {

    }

    @PostConstruct
    public void init() {

        perfil = "";

        initTree();
    }

    public void initTree() {
        totOpciones = 0;
        opcionesdisp = new ArrayList();
        opcionesPorPerfil = new ArrayList();
        option_values = new HashMap();
        sistemas = new ArrayList<>();
        sistemas = sistemasFacade.getSistemas();
        ventanas = new ArrayList<>();
        opciones = new ArrayList<>();
        root = null;

        option_values = new HashMap();
        root = new DefaultTreeNode("Root", null);
        TreeNode admin = new DefaultTreeNode("CRUD", root);
        TreeNode reports = new DefaultTreeNode("Reportes", root);
        TreeNode proc = new DefaultTreeNode("Procesos", root);
        System.out.println("entra");
        opcionesPorPerfil = getOpcionesPerfil();
        for (XeoxpOpcpe r : opcionesPorPerfil) {
            System.out.println("Opciones" + r.getXeoxpFecasi());
        }
        System.out.println("sale");

        sistemas.forEach(sistema -> {
            TreeNode t = new DefaultTreeNode(sistema.getXesisDescri(), admin);

            ventanas = ventanaFacade.getSistemas(sistema);
            ventanas.forEach(ventana -> {
                if (ventana.getXevenDescri().equals("CRUD")) {
                    opciones = opcionFacade.getOpciones(ventana.getXevenCodigo());
                    TreeNode tmp;
                    if (opciones.size() > 0) {
                        opcionesdisp.add(opciones);

                        for (int l = 0; l < opcionesPorPerfil.size(); l++) {
                            for (int k = 0; k < opciones.size(); k++) {
                                if (opciones.get(k).getXeopcOpcioPK().equals(opcionesPorPerfil.get(l).getXeopcOpcio().getXeopcOpcioPK())) {
                                    System.out.println("Asignado");
                                    option_values.put(totOpciones, opciones.get(k));
                                }
                            }
                        }
                        totOpciones++;
                        //opcionSeleccionada.add(opciones.get(0));
                        tmp = new DefaultTreeNode("Opc", opciones, t);
                    }
                }
            });
        });

        sistemas.forEach(sistema -> {
            TreeNode t = new DefaultTreeNode(sistema.getXesisDescri(), proc);

            ventanas = ventanaFacade.getSistemas(sistema);
            ventanas.forEach(ventana -> {

                if (ventana.getXevenDescri().equals("Procesos")) {

                    opciones = (List<XeopcOpcio>) ventana.getXeopcOpcioCollection();
                    TreeNode tmp;
                    if (opciones.size() > 0) {
                        opcionesdisp.add(opciones);

                        for (int l = 0; l < opcionesPorPerfil.size(); l++) {
                            for (int k = 0; k < opciones.size(); k++) {
                                if (opciones.get(k).getXeopcOpcioPK().equals(opcionesPorPerfil.get(l).getXeopcOpcio().getXeopcOpcioPK())) {
                                    System.out.println("Asignado");
                                    option_values.put(totOpciones, opciones.get(k));
                                }
                            }
                        }
                        totOpciones++;
                        //opcionSeleccionada.add(opciones.get(0));
                        tmp = new DefaultTreeNode("Opc", opciones, t);
                    }
                }
            });
        });

        sistemas.forEach(sistema -> {
            TreeNode t = new DefaultTreeNode(sistema.getXesisDescri(), reports);

            ventanas = ventanaFacade.getSistemas(sistema);
            ventanas.forEach(ventana -> {

                if (ventana.getXevenDescri().equals("Reportes")) {
                    opciones = (List<XeopcOpcio>) ventana.getXeopcOpcioCollection();
                    TreeNode tmp;
                    if (opciones.size() > 0) {
                        opcionesdisp.add(opciones);

                        for (int l = 0; l < opcionesPorPerfil.size(); l++) {
                            for (int k = 0; k < opciones.size(); k++) {
                                if (opciones.get(k).getXeopcOpcioPK().equals(opcionesPorPerfil.get(l).getXeopcOpcio().getXeopcOpcioPK())) {
                                    System.out.println("Asignado");
                                    option_values.put(totOpciones, opciones.get(k));
                                }
                            }
                        }
                        totOpciones++;
                        //opcionSeleccionada.add(opciones.get(0));
                        tmp = new DefaultTreeNode("Opc", opciones, t);
                    }
                }
            });
        });

        for (int i = 0; i < totOpciones; i++) {
            if (!option_values.containsKey(i)) {
                option_values.put(i, null);
            }
        }
        opcionSeleccionada = new Object[totOpciones];
    }

    public int getValue(List<XeopcOpcio> opciones) {
        /*System.out.println("tot getVa: " + totOpciones);
        System.out.println("Index of: " + opcionesdisp.indexOf(opciones));
        System.out.println("Opciones selec " + opcionSeleccionada.length);
        System.out.println("Valor: " + opcionSeleccionada[opcionesdisp.indexOf(opciones)]);*/
        return (int) opcionesdisp.indexOf(opciones);
    }

    public List<XeoxpOpcpe> getOpcionesPerfil() {

        return opcionperfilFacade.getOpcionPerfilP(perfil);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void getValueOf(Object n) {
        System.out.println(n);
    }

    public void manageChange(AjaxBehaviorEvent evt) {
        System.out.println("mc");
        System.out.println(evt.getBehavior());
        System.out.println(option_values);
    }

    public void manageChange2(ValueChangeEvent evt) {
        System.out.println("mc2");
        if (evt.getNewValue() != null) {
            System.out.println(evt.getNewValue());
        }
        if (evt.getOldValue() != null && evt.getNewValue() == null) {
            System.out.println(evt.getOldValue());
            //delete
            XeopcOpcio t = (XeopcOpcio) evt.getOldValue();
            if (perfil != null) {
                String[] s = perfil.split("-");
                List<XeoxpOpcpe> lista = new ArrayList<>();
                lista = opcionperfilFacade.getElements(s[0], t.getXeopcOpcioPK().getXeopcCodigo(), t.getXeopcOpcioPK().getXevenCodigo());
                for (XeoxpOpcpe li : lista) {
                    opcionperfilFacade.remove(li);
                }

                /*
                opcionperfilFacade.remove(opcionperfilFacade.getElement(s[0],
                        t.getXeopcOpcioPK().getXeopcCodigo(),
                        t.getXeopcOpcioPK().getXevenCodigo()));
                 */
            } else {
                List<XeoxpOpcpe> lista = new ArrayList<>();
                lista = opcionperfilFacade.getElements(perfil, t.getXeopcOpcioPK().getXeopcCodigo(), t.getXeopcOpcioPK().getXevenCodigo());
                for (XeoxpOpcpe li : lista) {
                    opcionperfilFacade.remove(li);
                }

                /*
                opcionperfilFacade.remove(opcionperfilFacade.getElement(perfil,
                        t.getXeopcOpcioPK().getXeopcCodigo(),
                        t.getXeopcOpcioPK().getXevenCodigo()));
                 */
            }

        }
    }

    @Asynchronous
    private void persistOptions() {
        try {

            for (Map.Entry<Integer, Object> entry : option_values.entrySet()) {
                Boolean entrar = false;
                if (entry.getValue() != null) {
                    System.out.println("entry.getValue(): " + entry.getValue());
                    String str = (String) entry.getValue();
                    Pattern pattern = Pattern.compile("xevenCodigo=(\\d+),\\s+xeopcCodigo=(\\d+)");
                    Matcher matcher = pattern.matcher(str);
                    matcher.find();
                    String xevenCodigo = matcher.group(1);
                    String xeopcCodigo = matcher.group(2);
                    //XeopcOpcio value = (XeopcOpcio) entry.getValue();
                    entrar = true;
                    List<XeopcOpcio> lista = opcionFacade.getOpciones(xeopcCodigo);
                    XeopcOpcio value = lista.get(0);
                    if (value != null) {
                        XeoxpOpcpe t = new XeoxpOpcpe();
                        java.util.Date fecha = new java.util.Date();
                        XeperPerfi x = new XeperPerfi("01");
                        List<XeoxpOpcpe> listaopcionesPorPerfil2 = opcionperfilFacade.getOpciones();
                        int cont = 1;
                        HashMap<Integer, Integer> l = new HashMap<Integer, Integer>();
                        for (XeoxpOpcpe o : listaopcionesPorPerfil2) {
                            l.put(cont, Integer.valueOf(o.getXeoxpOpcpePK().getXeoxpOpcper()));
                        }
                        int maxValue = (Collections.max(l.values()));

                        String idOpcpe = String.valueOf(maxValue + 1);
                        XeoxpOpcpePK PK;
                        if (perfil != null) {
                            String[] s = perfil.split("-");
                            PK = new XeoxpOpcpePK(s[0], value.getXeopcOpcioPK().getXevenCodigo(), value.getXeopcOpcioPK().getXeopcCodigo(), idOpcpe);
                        } else {

                            PK = new XeoxpOpcpePK(perfil, value.getXeopcOpcioPK().getXevenCodigo(), value.getXeopcOpcioPK().getXeopcCodigo(), idOpcpe);
                        }

                        t.setXeoxpOpcpePK(PK);
                        t.setXeoxpFecasi(fecha);
                        t.setXeperPerfi(x);

                        t.setXeopcOpcio(value);
                        t.setXeoxpFecret(fecha);

                        if (perfil != null) {
                            String[] s = perfil.split("-");
                            opcionesPorPerfil.forEach(ope -> {
                                if (ope.getXeperPerfi().getXeperCodigo().equals(s[0])) {
                                    ope.getXeperPerfi().getXeoxpOpcpeList().forEach(opcion -> {
                                        System.out.println(opcion);
                                        System.out.println(value);
                                        if (opcion.getXeopcOpcio().getXeopcOpcioPK().equals(value.getXeopcOpcioPK())) {
                                            canReassign = false;
                                        }
                                    });
                                }
                            });

                        } else {
                            opcionesPorPerfil.forEach(ope -> {
                                if (ope.getXeperPerfi().getXeperCodigo().equals(perfil)) {
                                    ope.getXeperPerfi().getXeoxpOpcpeList().forEach(opcion -> {
                                        System.out.println(opcion);
                                        System.out.println(value);
                                        if (opcion.getXeopcOpcio().getXeopcOpcioPK().equals(value.getXeopcOpcioPK())) {
                                            canReassign = false;
                                        }
                                    });
                                }
                            });
                        }

                        if (canReassign) {
                            opcionperfilFacade.create(t);
                        }
                        canReassign = true;
                    }
                }

            }
            updating = false;
            perfil = "";
            JsfUtil.addSuccessMessage("Perfil actualizado con éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Hubo un error al guardar");
        }
    }

    public void save() {
        System.out.println(option_values);
        updating = true;
        JsfUtil.addSuccessMessage("Actualizando perfil ...");
        persistOptions();

    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Object[] getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    /*public void setOpcionSeleccionada(Object[] opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }*/
    public List<List<XeopcOpcio>> getOpcionesdisp() {
        return opcionesdisp;
    }

    public void setOpcionesdisp(List<List<XeopcOpcio>> opcionesdisp) {
        this.opcionesdisp = opcionesdisp;
    }

    public List<XeoxpOpcpe> getOpcionesPorPerfil() {
        return opcionesPorPerfil;
    }

    public void setOpcionesPorPerfil(List<XeoxpOpcpe> opcionesPorPerfil) {
        this.opcionesPorPerfil = opcionesPorPerfil;
    }

    public Object getOpcionS(List<XeopcOpcio> opciones) {
        return opcionSeleccionada[opcionesdisp.indexOf(opciones)];
    }

    public HashMap<Integer, Object> getOption_values() {
        return option_values;
    }

    public void setOption_values(HashMap<Integer, Object> option_values) {
        this.option_values = option_values;
    }

    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }

    public List<XesisSiste> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<XesisSiste> sistemas) {
        this.sistemas = sistemas;
    }

    public List<XeopcOpcio> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<XeopcOpcio> opciones) {
        this.opciones = opciones;
    }

    public XesisSisteFacade getSistemasFacade() {
        return sistemasFacade;
    }

    public void setSistemasFacade(XesisSisteFacade sistemasFacade) {
        this.sistemasFacade = sistemasFacade;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../menu.xhtml";
    }

}
