package ru.vhsroni.repl;

import ru.vhsroni.model.CarEntity;
import ru.vhsroni.model.PenaltyEntity;
import ru.vhsroni.service.CarService;
import ru.vhsroni.service.impl.CarServiceImpl;
import ru.vhsroni.service.impl.PenaltyServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MethodsUtil {

    private static final CarService carService = new CarServiceImpl();

    private static final PenaltyServiceImpl penaltyService = new PenaltyServiceImpl();

    private static final Scanner sc = new Scanner(System.in);

    public static void invokeMethod() {
        printAvailableServices();
        Class<?> serviceClass = sc.nextLine().equals("carService")
                ? CarServiceImpl.class : PenaltyServiceImpl.class;

        Method currentMethod = null;
        printAvailableMethods(serviceClass);

        while (currentMethod == null) {
            String methodName = sc.nextLine();
            currentMethod = findMethodByName(methodName, serviceClass);
        }

        Parameter[] params = currentMethod.getParameters();
        try {
            if (params.length == 0) {
                System.out.println("This method has no parameters.");
                Object methodResult = currentMethod.invoke(getServiceInstanceByClass(serviceClass));
                System.out.println(methodResult);
            } else {
                printParams(params);
                String[] stringValues = sc.nextLine().split(", ");
                Object[] paramsValues = convertAllParams(params, stringValues);
                Object methodResult = currentMethod.invoke(getServiceInstanceByClass(serviceClass),
                        paramsValues);
                System.out.println(methodResult);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println("There are problems with invocation this method");
        }

        System.out.println("You can call the following method. \n");
    }

    private static Object[] convertAllParams(Parameter[] parameters, String[] stringValues) {
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++)
            result[i] = convertParam(parameters[i].getType(), stringValues[i]);
        return result;
    }

    private static Object convertParam(Class<?> type, String value) {
        if (type.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(Long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(String.class)) {
            return value;
        } else if (type.equals(java.sql.Date.class)) {
            return getSqlDateFromString(value);
        } else if (type.equals(CarEntity.class)) {
            String[] entityParams = value.split(",");
            return new CarEntity(null, entityParams[1]);
        } else if (type.equals(PenaltyEntity.class)) {
            String[] entityParams = value.split(",");
            return new PenaltyEntity(null, Long.parseLong(entityParams[1]),
                    Integer.parseInt(entityParams[2]), getSqlDateFromString(entityParams[3]));
        }
        return null;
    }

    private static java.sql.Date getSqlDateFromString(String value) {
        java.sql.Date result = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(value);
            result = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Problems with date parsing. " +
                    "Enter the date correctly in the line below in the format `yyyy-mm-dd`");
            return getSqlDateFromString(sc.nextLine());
        }
        return result;
    }

    private static Object getServiceInstanceByClass(Class<?> clazz) {
        if (clazz.equals(CarServiceImpl.class)) return carService;
        else if (clazz.equals(PenaltyServiceImpl.class)) return penaltyService;
        System.out.println("Something went wrong and we don't know of such a service.");
        return null;
    }

    private static Method findMethodByName(String methodName, Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        System.out.println("You entered the name of the method incorrectly, so we couldn't find it.");
        System.out.println("Enter the correct method name in the line below.");
        return null;
    }

    private static void printParams(Parameter[] parameters) {
        System.out.println("This method has next parameters:");
        for (Parameter param : parameters) {
            System.out.println("1. `" + param.getName() + "`, type: " + param.getType());
        }
        System.out.println("All parameters should be entered in one line, separating them using `, `.");
        System.out.println("if you want to enter an instance of a custom class, instead enter the fields of its " +
                "AllArgsConstructor separated by commas without spaces.");
        System.out.println("Example: arg1, arg2, a3_1,a3_2,a3_3,a3_4, arg4");
    }

    private static void printAvailableServices() {
        System.out.println("Hi, this is a car service and you can choose service ou want to use:" +
                "(just enter the name in the line below)" +
                "\n1. carService" +
                "\n2. penaltyService");
    }

    private static void printAvailableMethods(Class<?> serviceClass) {
        if (serviceClass.equals(CarServiceImpl.class)) {
            System.out.println("There are available methods of carService (just enter methodName in the line below)" +
                    "\n1. findAllCars" +
                    "\n2. findCarById" +
                    "\n3. findCarByTitle" +
                    "\n4. saveNewCar" +
                    "\n5. deleteCarById" +
                    "\n6. updateCarById");
        } else {
            System.out.println("There are available methods of penaltyService (just enter methodName in the line below)" +
                    "\n1. findAllPenaltiesByCarId" +
                    "\n2. findAllPenaltiesWhereAmountLargerThan" +
                    "\n3. findAllPenaltiesOlderThanDate" +
                    "\n4. addNewPenalty" +
                    "\n5. findPenaltyById");
        }
    }
}
