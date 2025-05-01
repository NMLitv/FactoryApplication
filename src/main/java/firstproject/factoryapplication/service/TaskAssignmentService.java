package firstproject.factoryapplication.service;

import firstproject.factoryapplication.model.Employee;
import firstproject.factoryapplication.model.Task;
import firstproject.factoryapplication.repository.EmployeeRepository;
import firstproject.factoryapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Service
public class TaskAssignmentService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final ScheduleTaskService scheduleTaskService;
    private List<Employee> employeesFree;
    private List<Task> tasksActive;

    public TaskAssignmentService(EmployeeRepository employeeRepository, TaskRepository taskRepository, TaskService taskService, ScheduleTaskService scheduleTaskService) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.scheduleTaskService = scheduleTaskService;
    }

    public void initializeEmployees() {
        employeesFree = employeeRepository.findAll();
    }

    public void initializeTasks() {
        tasksActive = taskRepository.findAll();
    }

    // берется работник только который работает, нужно еще добавить провеку на то что в смене, но хз
    public List<Employee> filterEmployees() {
        initializeEmployees();
        employeesFree.removeIf(employee -> (employee.getStatus()).equals("work"));
        return employeesFree;
    }

    // берутся только задачи, которые не были назначены, так же поле отбора устанавливается приоритет каждой задаче
    public List<Task> filterTasks() {
        initializeTasks();
        tasksActive.removeIf(task -> !(task.getEmployee() == null));
        for (Task task : tasksActive) {
            taskService.setPriority(task);
        }
        scheduleTaskService.sortTasks(tasksActive);
        return tasksActive;
    }

    // нахождение ближайшего курьера для каждой задачи
    public void assigningTaskToNearestEmployee() {
        for (Task task : tasksActive) {
            double[] equipmentLocation = task.getEquipment().getLocation();
            List<Double> distances = new ArrayList<Double>();
            for (Employee employee : employeesFree) {
                double[] employeeLocation = employee.getLocation();
                double distance = calculateDistance(equipmentLocation, employeeLocation);
                distances.add(distance);
            }
            double shortestDistance = searchShortestDistance(distances);
            task.setEmployee(employeesFree.get(distances.indexOf(shortestDistance)));
        }
    }

    // нахождение кратчайшего расстояния для одной задачи
    private double searchShortestDistance(List<Double> distances) {
        double min = distances.get(0);
        for (Double distance : distances) {
            if (distance < min) min = distance;
        }
        return min;
    }

    // расчёт расстояния от оборудования до курера
    private double calculateDistance(double[] equipmentLocation, double[] employeeLocation) {
        double distance = sqrt(pow(equipmentLocation[0] - employeeLocation[0], 2) + pow(equipmentLocation[1] - employeeLocation[1], 2));
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        return Double.parseDouble(df.format(distance));
    }
}
