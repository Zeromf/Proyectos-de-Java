/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import java.util.Date;
import static javafx.scene.input.KeyCode.S;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author enzo
 */
public class Cliente_id {
   
    public int create(String nombre, String apellido, String dni, Date fechaNaci, String direccion, Integer telefono){
        Cliente user=new Modelo.Cliente( nombre,  apellido,  dni,  fechaNaci,  direccion, telefono);
        
        SessionFactory sesion= NewHibernateUtil.getSessionFactory();
        Session session= sesion.openSession();
        Transaction tx= session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        JOptionPane.showMessageDialog(null,"Insertado correctamente");
        
        return 1;
    }
         
     public Cliente getDatos(String clave,String valor){
     
        Cliente cliente= new Cliente();
        SessionFactory sesion= NewHibernateUtil.getSessionFactory();
        Session e= sesion.openSession();
        Transaction tx=e.beginTransaction();
        if (clave.equals("Codigo")) {
             int v=Integer.parseInt(valor);
             cliente =(Cliente)e.get(Modelo.Cliente.class,v);

        }else{
            Query q=e.createQuery("from Cliente Where "+ clave.toLowerCase()+"= "+valor);
            cliente =(Cliente)q.uniqueResult();
             
             
         }


        tx.commit();
        e.close( );
        return cliente;
                 
         
     
     
     
     }    
    
         
   public Cliente updateCliente(Integer id, String nombre, String apellido,String DNI,String direccion,int telefono,Date fecha) {
       Cliente cli= new Cliente();

        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session S = sesion.openSession();
        Transaction tx= S.beginTransaction();
        cli=(Cliente)S.get(Modelo.Cliente.class,id);
        cli.setNombre(nombre);
        cli.setApellido(apellido);        
        cli.setDni(DNI);
        cli.setDireccion(direccion);
        cli.setTelefono(telefono);
        cli.setFechaNaci(fecha);
        S.update(cli);
        tx.commit();
        S.close();
        return cli;
   }


   
    public Cliente DeleteCliente(Integer id){
        Cliente cli= new Cliente();

        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session S = sesion.openSession();
        Transaction tx= S.beginTransaction();
        cli=(Cliente)S.get(Modelo.Cliente.class,id);
        S.delete(cli);
        tx.commit();
        S.close();
        
        return cli;
    
    }











}



