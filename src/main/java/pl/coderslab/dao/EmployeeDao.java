package pl.coderslab.dao;

public class EmployeeDao {

    public static void saveToDb(Employee employee) {
        if (emplyee.getId() == 0) {
            String query = "INSERT INTO Employee (name, surename, adress, phonenumber, note, workhours) VALUES(?,?,?,?,?,?)";
            List<String> params = new ArrayList<>();
            params.add(employee.getName());
            params.add(employee.getSurename());
            params.add(employee.getAdress());
            params.add(String.valueOf(employee.getPhonenumber()));
            params.add(employee.getNote());
            params.add(String.valueOf(employee.getWorkhours()));
            try {
                Integer id = DbService.insertIntoDatabase(query, params);
                if (id != null) {
                    employee.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "UPDATE Employee SET name = ?, surename = ?, adress = ?, phonenumber = ?, note = ?, workhours =? WHERE id = ?";
            List<String> params = new ArrayList<>();
            params.add(employee.getName());
            params.add(employee.getSurename());
            params.add(employee.getAdress());
            params.add(String.valueOf(employee.getPhonenumber()));
            params.add(employee.getNote());
            params.add(String.valueOf(employee.getWorkhours()));

            params.add(String.valueOf(employee.getId()));
            try {
                DbService.executeUpdate(query, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM Employee WHERE id =?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        try {
            int affectedRows = DbService.executeUpdate(query, params);
            if (affectedRows > 0) {
                System.out.println("Usunalem pracownika o id: " + id);
            } else {
                System.out.println("Operacja usuniecia zadania zakonczyla sie niepowodzeniem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Employee> loadAll() {

        ArrayList<Customer> customer = new ArrayList<>();
        String query = "SELECT id, name, surename, adress, phonenumber, note, workhours FROM Employee";
        try {
            List<String[]> rows = DbService.getData(query, null);
            for (String[] row : rows) {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(row[0]));
                employee.setName(row[1]);
                employee.setSurename(row[2]);
                employee.setAdress(row[3]);
                employee.setPhonenumber(Integer.parseInt(row[4]));
                employee.setNote(row[5]);
                employee.setWorkhours(Integer.parseInt(row[6]));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return employees;
    }

    public static Employee loadById(int id) {
        String query = "SELECT id, name, surename, adress, phonenumber, note, workhours FROM Employee WHERE id = ?";
        try {
            ArrayList<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query, params);
            for (String[] row : rows) {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(row[0]));
                employee.setName(row[1]);
                employee.setSurename(row[2]);
                employee.setAdress(row[3]);
                employee.setPhonenumber(Integer.parseInt(row[4]));
                employee.setNote(row[5]);
                employee.setWorkhours(Integer.parseInt(row[6]));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}