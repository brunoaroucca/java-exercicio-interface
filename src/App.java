import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class App {

    public static void main(String[] args) throws Exception {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data");
        System.out.print("Number: ");
        int numberContract = sc.nextInt();

        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());

        System.out.print("Contract value: ");
        double valueContract = sc.nextDouble();

        Contract contract = new Contract(numberContract, date, valueContract);

        System.out.print("Enter number of installment: ");
        int numberMonths = sc.nextInt();
        
        new ContractService(new PaypalService()).processContract(contract, numberMonths);
        

        System.out.println("Installments: ");
        for (Installment installment : contract.getInstallments()){
            System.out.println(sdf.format(installment.getDueDate()) + " - " + installment.getAmount());
        }

        sc.close();
    }
}
