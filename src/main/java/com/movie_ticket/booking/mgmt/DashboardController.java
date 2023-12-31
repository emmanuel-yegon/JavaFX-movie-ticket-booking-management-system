package com.movie_ticket.booking.mgmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.*;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane topForm;

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_date;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_movieTitle;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<MoviesData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<MoviesData, String> availableMovies_col_showingDate;

    @FXML
    private TableColumn<MoviesData, String> availableMovies_col_genre;

    @FXML
    private TableColumn<MoviesData, String> availableMovies_col_movieTitle;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<Integer> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<Integer> availableMovies_specialClass_quantity;

    @FXML
    private TableView<MoviesData> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<CustomerData, String> customers_col_date;

    @FXML
    private TableColumn<CustomerData, String> customers_col_movieTitle;

    @FXML
    private TableColumn<CustomerData, String> customers_col_ticketNumber;

    @FXML
    private TableColumn<CustomerData, String> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private TableView<CustomerData> customers_tableView;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnToday;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_deleteBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<MoviesData> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_updateBtn;

    @FXML
    private Button minimize;

    @FXML
    private Button signout;

    @FXML
    private Label username;

    private Image image;
    private double x = 0;
    private double y = 0;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet rs;


    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;

    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int qty1 = 0;
    private int qty2 = 0;

    public void searchCustomer() {

        FilteredList<CustomerData> filter = new FilteredList<>(custList, e -> true);

        customers_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateCustomer -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if (predicateCustomer.getId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getMovieTitle().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getDate().toString().contains(searchKey)) {
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<CustomerData> sort = new SortedList<>(filter);

        sort.comparatorProperty().bind(customers_tableView.comparatorProperty());
        customers_tableView.setItems(sort);
    }

    public ObservableList<CustomerData> customerList() {

        ObservableList<CustomerData> customerL = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer";

        connect = MovieDb.connectDb();

        try {

            CustomerData customerD;

            prepare = connect.prepareStatement(sql);
            rs = prepare.executeQuery();

            while (rs.next()) {

                customerD = new CustomerData(rs.getInt("id")
                        , rs.getString("type")
                        , rs.getString("movieTitle")
                        , rs.getInt("quantity")
                        , rs.getDouble("total")
                        , rs.getDate("date")
                        , rs.getTime("time"));

                customerL.add(customerD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerL;
    }

    private ObservableList<CustomerData> custList;

    public void showCustomerList() {

        custList = customerList();

        customers_col_ticketNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        customers_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));

        customers_tableView.setItems(custList);
    }

    public void selectCustomerList() {

        CustomerData custD = customers_tableView.getSelectionModel().getSelectedItem();
        int num = customers_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        customers_ticketNumber.setText(String.valueOf(custD.getId()));
        customers_movieTitle.setText(custD.getMovieTitle());
        customers_date.setText(String.valueOf(custD.getDate()));
        customers_time.setText(String.valueOf(custD.getTime()));

    }

    public void deleteCustomer() {

        String sql = "DELETE FROM customer WHERE id='" + customers_ticketNumber.getText() + "'";

        connect = MovieDb.connectDb();

        try {

            Alert alert;

            statement = connect.createStatement();

            if (customers_ticketNumber.getText().isEmpty()
                    || customers_movieTitle.getText().isEmpty()
                    || customers_date.getText().isEmpty()
                    || customers_time.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the customer first!");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete: " + customers_movieTitle.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");

                    clearCustomer();
                    showCustomerList();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCustomer() {

        customers_ticketNumber.setText("");
        customers_movieTitle.setText("");
        customers_date.setText("");
        customers_time.setText("");

    }

    public void receipt() {

        if (total > 0) {

            HashMap hash = new HashMap();
            hash.put("receipt", num);

            try {
                JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\EMMANUEL-YEGON\\Documents\\NetBeansProjects\\Movie Ticket Booking Mgmt\\src\\movie\\ticket\\booking\\mgmt\\report.jrxml");
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, connect);

                JasperViewer.viewReport(jPrint, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid.");
            alert.showAndWait();

        }


    }

    private int num;
    private int qty;

    public void buy() {

        String sql = "INSERT INTO customer (type,movieTitle,quantity,total,date,time) VALUES(?,?,?,?,?,?)";

        connect = MovieDb.connectDb();
        String type = "";

        if (price1 > 0 && price2 == 0) {
            type = "Special Class";
        } else if (price2 > 0 && price1 == 0) {
            type = "Normal Class";
        } else if (price2 > 0 && price1 > 0) {
            type = "Special Class & Normal Class";
        }

        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        try {

            LocalTime localTime = LocalTime.now();
            Time time = Time.valueOf(localTime);

            qty = qty1 + qty2;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, type);
            prepare.setString(2, availableMovies_title.getText());
            prepare.setString(3, String.valueOf(qty));
            prepare.setString(4, String.valueOf(total));
            prepare.setString(5, String.valueOf(setDate));
            prepare.setString(6, String.valueOf(time));

            Alert alert;

            if (availableMovies_imageView.getImage() == null
                    || availableMovies_title.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            } else if (price1 == 0 && price2 == 0) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the quantity of ticket you want to purchase.");
                alert.showAndWait();

            } else {

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully purchased!.");
                alert.showAndWait();

                String sql1 = "SELECT * FROM customer";

                prepare = connect.prepareStatement(sql1);
                rs = prepare.executeQuery();

                num = 0;

                while (rs.next()) {
                    num = rs.getInt("id");
                }

                String sql2 = "INSERT INTO customer_info (customer_id,type,total,movieTitle,quantity) VALUES(?,?,?,?,?)";

                prepare = connect.prepareStatement(sql2);
                prepare.setString(1, String.valueOf(num));
                prepare.setString(2, type);
                prepare.setString(3, String.valueOf(total));
                prepare.setString(4, availableMovies_title.getText());
                prepare.setString(5, String.valueOf(qty));

                prepare.execute();

                clearPurchaseTicketInfo();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearPurchaseTicketInfo() {

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        availableMovies_specialClass_quantity.setValueFactory(spinner1);
        availableMovies_normalClass_quantity.setValueFactory(spinner2);

        availableMovies_specialClass_price.setText("$0.0");
        availableMovies_normalClass_price.setText("$0.0");
        availableMovies_total.setText("$0.0");

        availableMovies_imageView.setImage(null);
        availableMovies_title.setText("");


    }

    public void showSpinnerValue() {
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        availableMovies_specialClass_quantity.setValueFactory(spinner1);
        availableMovies_normalClass_quantity.setValueFactory(spinner2);

    }

    public void getSpinnerValue(MouseEvent event) {

        qty1 = availableMovies_specialClass_quantity.getValue();
        qty2 = availableMovies_normalClass_quantity.getValue();

        price1 = (qty1 * 25);
        price2 = (qty2 * 10);

        total = (price1 + price2);

        availableMovies_specialClass_price.setText("$" + String.valueOf(price1));
        availableMovies_normalClass_price.setText("$" + String.valueOf(price2));

        availableMovies_total.setText("$" + String.valueOf(total));


    }

    public ObservableList<MoviesData> availableMoviesList() {

        ObservableList<MoviesData> listAvMovies = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie WHERE current ='Showing'";

        connect = MovieDb.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            rs = prepare.executeQuery();

            MoviesData moviesData;

            while (rs.next()) {
                moviesData = new MoviesData(rs.getInt("id")
                        , rs.getString("movieTitle")
                        , rs.getString("genre")
                        , rs.getString("duration")
                        , rs.getString("image")
                        , rs.getDate("date")
                        , rs.getString("current"));

                listAvMovies.add(moviesData);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAvMovies;

    }

    private ObservableList<MoviesData> availableMoviesList;

    public void showAvailableMovies() {

        availableMoviesList = availableMoviesList();

        availableMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showingDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableMovies_tableView.setItems(availableMoviesList);

    }


    public void selectAvailableMovies() {

        MoviesData moviesData = availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableMovies_movieTitle.setText(moviesData.getMovieTitle());
        availableMovies_genre.setText(moviesData.getGenre());
        availableMovies_date.setText(String.valueOf(moviesData.getDate()));

        GetData.path = moviesData.getImage();
        GetData.movieTitle = moviesData.getMovieTitle();

    }

    public void selectMovie() {

        Alert alert;

        if (availableMovies_movieTitle.getText().isEmpty()
                || availableMovies_genre.getText().isEmpty()
                || availableMovies_date.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the movie first");
            alert.showAndWait();

        } else {
            String uri = "file:" + GetData.path;

            image = new Image(uri, 135, 180, false, true);
            availableMovies_imageView.setImage(image);

            availableMovies_title.setText(GetData.movieTitle);

            availableMovies_movieTitle.setText("");
            availableMovies_genre.setText("");
            availableMovies_date.setText("");
        }

    }

    private String[] currentList = {"Showing", "End Showing"};

    public void comboBox() {
        List<String> listCurrent = new ArrayList<>();

        for (String data : currentList) {
            listCurrent.add(data);
        }

        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);

    }

    public void updateEditScreening() {

        String sql = "UPDATE movie SET current = '" + editScreening_current.getSelectionModel().getSelectedItem()
                + "'WHERE movieTitle = '" + editScreening_title.getText() + "'";

        connect = MovieDb.connectDb();

        try {
            statement = connect.createStatement();

            Alert alert;

            if (editScreening_title.getText().isEmpty()
                    || editScreening_imageView.getImage() == null
                    || editScreening_current.getSelectionModel().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            } else {
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                showEditScreening();
                clearEditScreening();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearEditScreening() {

        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
//        editScreening_current.setSelectionModel(null);

    }

    public void searchEditScreening() {

        FilteredList<MoviesData> filter = new FilteredList<>(editScreeningL, e -> true);

        editScreening_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateMoviesData.getMovieTitle().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateMoviesData.getCurrent().toLowerCase().contains(searchKey)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<MoviesData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(editScreening_tableView.comparatorProperty());
        editScreening_tableView.setItems(sortData);

    }

    public void selectEditScreening() {
        MoviesData moviesData = editScreening_tableView.getSelectionModel().getSelectedItem();
        int num = editScreening_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        String uri = "file:" + moviesData.getImage();
        image = new Image(uri, 140, 183, false, true);
        editScreening_imageView.setImage(image);
        editScreening_title.setText(moviesData.getMovieTitle());

    }

    public ObservableList<MoviesData> editScreeningList() {

        ObservableList<MoviesData> editSList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = MovieDb.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            rs = prepare.executeQuery();

            MoviesData moviesData;

            while (rs.next()) {

                moviesData = new MoviesData(rs.getInt("id")
                        , rs.getString("movieTitle")
                        , rs.getString("genre")
                        , rs.getString("duration")
                        , rs.getString("image")
                        , rs.getDate("date")
                        , rs.getString("current"));

                editSList.add(moviesData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return editSList;
    }

    private ObservableList<MoviesData> editScreeningL;

    public void showEditScreening() {
        editScreeningL = editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        editScreening_tableView.setItems(editScreeningL);

    }

    public void searchAddMovies() {

        FilteredList<MoviesData> filter = new FilteredList<>(listAddMovies, e -> true);

        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateMoviesData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String keySearch = newValue.toLowerCase();

                if (predicateMoviesData.getMovieTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDate().toString().contains(keySearch)) {
                    return true;
                }

                return false;
            });

        });

        SortedList<MoviesData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());
        addMovies_tableView.setItems(sortData);

    }

    public void importImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Choose Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));


        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {

            image = new Image(file.toURI().toString(), 130, 160, false, true);
            addMovies_imageView.setImage(image);

            GetData.path = file.getAbsolutePath();

        }
    }

    public void movieId() {

        String sql = "SELECT count(id) FROM movie";

        connect = MovieDb.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            rs = prepare.executeQuery();

            if (rs.next()) {
                GetData.movieId = rs.getInt("count(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAddMovies() {

        String sql1 = "SELECT * FROM movie WHERE movieTitle = '" + addMovies_movieTitle.getText() + "'";

        connect = MovieDb.connectDb();

        Alert alert;

        try {

            statement = connect.createStatement();
            rs = statement.executeQuery(sql1);

            if (rs.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + " already exist!");
                alert.showAndWait();
            } else {
                if (addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields!");
                    alert.showAndWait();
                } else {

                    String sql = "INSERT INTO movie(movieTitle,genre,duration,image,date,current) VALUES(?,?,?,?,?,?)";

                    String uri = GetData.path;
                    uri = uri.replace("\\", "\\\\");


                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addMovies_movieTitle.getText());
                    prepare.setString(2, addMovies_genre.getText());
                    prepare.setString(3, addMovies_duration.getText());
                    prepare.setString(4, uri);
                    prepare.setString(5, String.valueOf(addMovies_date.getValue()));
                    prepare.setString(6, "Showing");

                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully add new movie!");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<MoviesData> addMoviesList() {
        ObservableList<MoviesData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = MovieDb.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            rs = prepare.executeQuery();

            MoviesData moviesData;

            while (rs.next()) {
                moviesData = new MoviesData(rs.getInt("id")
                        , rs.getString("movieTitle")
                        , rs.getString("genre")
                        , rs.getString("duration")
                        , rs.getString("image")
                        , rs.getDate("date")
                        , rs.getString("current"));

                listData.add(moviesData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    public void updateAddMovies() {

        String uri = GetData.path;
        uri = uri.replace("\\", "\\\\");


        String sql = "UPDATE movie SET movieTitle='" + addMovies_movieTitle.getText()
                + "', genre='" + addMovies_genre.getText()
                + "', duration='" + addMovies_duration.getText()
                + "', image='" + uri
                + "', date = '" + addMovies_date.getValue() + "'  WHERE id='" + String.valueOf(GetData.movieId) + "'";

        connect = MovieDb.connectDb();

        try {

            statement = connect.createStatement();

            Alert alert;

            if (addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first!");
                alert.showAndWait();


            } else {

                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated" + addMovies_movieTitle.getText());
                alert.showAndWait();

                clearAddMoviesList();
                showAddMoviesList();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteAddMovies() {

        String sql = "DELETE FROM movie WHERE movieTitle='" + addMovies_movieTitle.getText() + "'";

        connect = MovieDb.connectDb();

        try {

            statement = connect.createStatement();

            Alert alert;

            if (addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_date.getValue() == null
                    || addMovies_imageView.getImage() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first!");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete " + addMovies_movieTitle.getText());

                Optional<ButtonType> option = alert.showAndWait();

                if (ButtonType.OK.equals(option.get())) {

                    statement.executeUpdate(sql);

                    showAddMoviesList();
                    clearAddMoviesList();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted movie");

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAddMoviesList() {

        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);

    }

    private ObservableList<MoviesData> listAddMovies;

    public void showAddMoviesList() {
        listAddMovies = addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        addMovies_tableView.setItems(listAddMovies);

    }

    public void selectAddMoviesList() {

        MoviesData moviesData = addMovies_tableView.getSelectionModel().getSelectedItem();
        int run = addMovies_tableView.getSelectionModel().getSelectedIndex();

        if ((run - 1) < -1) {
            return;
        }

        GetData.path = moviesData.getImage();

        GetData.movieId = moviesData.getId();

        addMovies_movieTitle.setText(moviesData.getMovieTitle());
        addMovies_genre.setText(moviesData.getGenre());
        addMovies_duration.setText(moviesData.getDuration());
        String getDate = String.valueOf(moviesData.getDate());


        String uri = "file:" + moviesData.getImage();

        image = new Image(uri, 130, 160, false, true);
        addMovies_imageView.setImage(image);

        addMovies_date.setValue(moviesData.getDate().toLocalDate());

    }

    public void logout() {

        signout.getScene().getWindow().hide();

        try {

            Parent root = FXMLLoader.load(getClass().getResource("movie-mgmt.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {

            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:#ae2d3c");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addMovies_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            addMovies_btn.setStyle("-fx-background-color:#ae2d3c");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

            showAddMoviesList();

        } else if (event.getSource() == availableMovies_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            availableMovies_btn.setStyle("-fx-background-color:#ae2d3c");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

            showAvailableMovies();

        } else if (event.getSource() == editScreening_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);

            editScreening_btn.setStyle("-fx-background-color:#ae2d3c");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

            showEditScreening();

        } else if (event.getSource() == customers_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);

            customers_btn.setStyle("-fx-background-color:#ae2d3c");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");

            showCustomerList();
        }

    }

    public void displayUsername() {
        username.setText(GetData.username);
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();

        showAddMoviesList();

        showEditScreening();

        comboBox();

        showAvailableMovies();

        showSpinnerValue();

        showCustomerList();
    }
}
