package ut.entity.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import ut.Main;
import ut.entity.Product;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by AUser on 2015-12-9 009.
 */


public class ProductAction extends ActionSupport implements ModelDriven<Product> {
    private Product product;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("execute");

        ActionContext actionContext = ActionContext.getContext();
        Map request = (Map) actionContext.get("request");
        request.put("user", "Adiljan.Yasin" + System.currentTimeMillis() % 100);
        String name = actionContext.getName();
//        ActionContext.getContext().put("user","ActionContext");
        System.out.println("执行：" + name);
        return super.execute();
    }


    public String delete() throws IOException {
        Session session = Main.getSession();
        Query query = session.createQuery("delete from Product where id=?");
        query.setInteger(0, id);
        query.executeUpdate();
        HttpServletResponse response = ServletActionContext.getResponse();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("info", "success");
        builder.add("id", id);
        String s = builder.build().toString();
        response.getWriter().write(s);
        return null;
    }


    public String list() throws Exception {
        System.out.println("list");
        Session session = Main.getSession();
        List list = session.createQuery("from Product p order by p.name").setMaxResults(5).list();
//        System.out.println("list:"+list);
        ActionContext.getContext().put("list", list);

        return SUCCESS;
    }

    public String insert() throws Exception {
        System.out.println("insert");
        execute();
        return SUCCESS;
    }

    public String insert_post() {
        System.out.println("insert_post:" + product);
        Session session = Main.getSession();

        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/JSON; charset=utf-8");
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("info", "success");
            builder.add("id", getModel().getId());
            String s = builder.build().toString();
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Product getModel() {
        if (this.product == null) {
            if (this.id == 0) {
                this.product = new Product();
                this.product.setInsertTime(new Date());
            } else {
                Session session = Main.getSession();
                Query query = session.createQuery("from  Product a where a.id=?");
                this.product = (Product) query.setInteger(0, id).uniqueResult();
            }
        }
        return this.product;
    }

}
