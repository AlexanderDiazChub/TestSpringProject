package service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexander on 08/05/2018.
 */
@RestController
public class OperController {

    public enum Operation {
        add,
        subtract,
        multiply,
        divide
    }

    @RequestMapping("/oper")
    public OperResult oper(
            @RequestParam(value = "a", required = false) String aParam,
            @RequestParam(value = "b", required = false) String bParam,
            @RequestParam(value = "op", required = false) String opParam
    ) {

        double a = getNumParam(aParam);
        if (Double.isNaN(a)) {
            return new OperResult("Error: param a must be double", Double.NaN);
        }

        double b = getNumParam(bParam);
        if (Double.isNaN(b)) {
            return new OperResult("Error: param b must be double", Double.NaN);
        }

        Operation operation = null;
        if ("add".equals(opParam)) {
            operation = Operation.add;
        } else if ("sub".equals(opParam)) {
            operation = Operation.subtract;
        } else if ("mul".equals(opParam)) {
            operation = Operation.multiply;
        } else if ("div".equals(opParam)) {
            operation = Operation.divide;
        }
        if (operation == null) {
            return new OperResult("Error: param op must be 'add', 'sub', 'mul' or 'div'", Double.NaN);
        }

        double result;
        switch (operation) {
            case add:
                result = a + b;
                break;
            case subtract:
                result = a - b;
                break;
            case multiply:
                result = a * b;
                break;
            case divide:
                if (b == 0) {
                    return new OperResult("Error: division by 0", Double.NaN);
                }
                result = a / b;
                break;
            default:
                return new OperResult("Error: unknown operation", Double.NaN);
        }

        if (Double.isNaN(result)) {
            return new OperResult("Error: unknown error", Double.NaN);

        } else {
            return new OperResult("ok", result);
        }
    }

    private double getNumParam(String param) {
        if (param == null) {
            return Double.NaN;
        }
        try {
            return Double.parseDouble(param);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }


    public static class OperResult {

        private final String status;
        private final double result;


        public OperResult(String status, double result) {
            this.status = status;
            this.result = result;
        }

        public String getStatus() {
            return status;
        }

        public double getResult() {
            return result;
        }
    }


}
