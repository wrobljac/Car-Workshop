package pl.coderslab.dao;

import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    ///    public Order(int id, Date admissionDate, Date plannedServiceDate, Date serviceDate, Employee employee,
    // String carProblemDescription, String carFixDescription, Status status, Vehicle vehicle, float fixCosts, float partsCosts,
    // int customer_id, int employee_id, int vehicle_id) {


    ///    Date plannedServiceDate, Date serviceDate, Employee employee,
    // String carProblemDescription, String carFixDescription, Status status, Vehicle vehicle, float fixCosts, float partsCosts,
    // int customer_id, int employee_id, int vehicle_id) {
    public static void saveToDb(Order order) {
        if (order.getId() == 0) {
            String query = "INSERT INTO Order (admissionDate, plannedServiceDate, serviceDate, carProblemDescription, carFixDescription, " +
                    "fixCosts, partsCosts, customer_id, employee_id, vehicle_id)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?)";
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(order.getAdmissionDate()));
            params.add(String.valueOf(order.getPlannedServiceDate()));
            params.add(String.valueOf(order.getServiceDate()));
            //Employee empleyee

            params.add(order.getCarProblemDescription());
            params.add(order.getCarFixDescription());
            //Status status
            //Vehicle vehicle

            params.add(String.valueOf(order.getFixCosts()));
            params.add(String.valueOf(order.getPartsCosts()));
            //referencje na inne tabele

            params.add(String.valueOf(order.getCustomer_id()));
            params.add(String.valueOf(order.getEmployee_id()));
            params.add(String.valueOf(order.getVehicle_id()));
            try {
                Integer id = DbService.insertIntoDatabase(query, params);
                if (id != null) {
                    order.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            String query = "UPDATE Order SET admissionDate = ? , plannedServiceDate = ? , serviceDate = ? , carProblemDescription = ? , carFixDescription = ? , " +
                    "fixCosts = ? , partsCosts = ? , customer_id = ? , employee_id = ? , vehicle_id = ?  WHERE id = ?";
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(order.getAdmissionDate()));
            params.add(String.valueOf(order.getPlannedServiceDate()));
            params.add(String.valueOf(order.getServiceDate()));
            //Employee empleyee

            params.add(order.getCarProblemDescription());
            params.add(order.getCarFixDescription());
            //Status status
            //Vehicle vehicle

            params.add(String.valueOf(order.getFixCosts()));
            params.add(String.valueOf(order.getPartsCosts()));
            //referencje na inne tabele

            params.add(String.valueOf(order.getCustomer_id()));
            params.add(String.valueOf(order.getEmployee_id()));
            params.add(String.valueOf(order.getVehicle_id()));

            params.add(String.valueOf(order.getId()));
            try {
                DbService.executeUpdate(query, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



    public static ArrayList<Order> loadAll() {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT id, admissionDate, plannedServiceDate, serviceDate, carProblemDescription, carFixDescription, " +
                "fixCosts, partsCosts, customer_id, employee_id, vehicle_id FROM Order";
        try {
            List<String[]> rows = DbService.getData(query, null);
            for (String[] row : rows) {

                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                Order order = new Order();
                order.setId(Integer.parseInt(row[0]));
                order.setAdmissionDate(df.parse(row[1]));
                order.setPlannedServiceDate(df.parse(row[2]));
                order.setServiceDate(df.parse(row[3]));
                //Employee empleyee

                order.setCarProblemDescription(row[4]);
                order.setCarFixDescription(row[5]);
                //Status status
                //Vehicle vehicle

                order.setFixCosts(Float.parseFloat(row[6]));
                order.setPartsCosts(Float.parseFloat(row[7]));
                //referencje z innych tablic

                order.setCustomer_id(Integer.parseInt(row[8]));
                order.setEmployee_id(Integer.parseInt(row[9]));
                order.setVehicle_id(Integer.parseInt(row[10]));

                //wczytanie i dodanie obiektow (brauej mi customer i employee
//                order.setEmployee(E)
//                order.setStatus(StatusDao.loadById())
                order.setVehicle(VehicleDao.loadById(order.getVehicle_id()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static Order loadById(int id) {
        String query = "SELECT id, admissionDate, plannedServiceDate, serviceDate, carProblemDescription, carFixDescription, " +
                "fixCosts, partsCosts, customer_id, employee_id, vehicle_id FROM Order WHERE id = ?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            List<String[]> rows = DbService.getData(query, null);
            for (String[] row : rows) {
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                Order order = new Order();
                order.setId(Integer.parseInt(row[0]));
                order.setAdmissionDate(df.parse(row[1]));
                order.setPlannedServiceDate(df.parse(row[2]));
                order.setServiceDate(df.parse(row[3]));
                //Employee empleyee

                order.setCarProblemDescription(row[4]);
                order.setCarFixDescription(row[5]);
                //Status status
                //Vehicle vehicle

                order.setFixCosts(Float.parseFloat(row[6]));
                order.setPartsCosts(Float.parseFloat(row[7]));
                //referencje z innych tablic

                order.setCustomer_id(Integer.parseInt(row[8]));
                order.setEmployee_id(Integer.parseInt(row[9]));
                order.setVehicle_id(Integer.parseInt(row[10]));

                //wczytanie i dodanie obiektow (brauej mi customer i employee
//                order.setEmployee(E)
//                order.setStatus(StatusDao.loadById())
                order.setVehicle(VehicleDao.loadById(order.getVehicle_id()));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteOrder(int id)  {
        String query = "DELETE FROM Order WHERE id = ?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            int affectedRows = DbService.executeUpdate(query, params);
            if (affectedRows > 0) {
                System.out.println("Usunalem zamowienie o id: " + id);
            } else {
                System.out.println("Operacja usuniecia zamowienia zakonczyla sie niepowodzeniem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
