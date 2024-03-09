package model.services;
import java.util.Calendar;
import java.util.Date;
import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
    
    OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer months){
        double quota = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++){

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(contract.getDate());
            calendar.add(Calendar.MONTH, i);
            Date date = calendar.getTime();
            
            double updateQuota = quota + paymentService.interest(quota, i);
            double fullQuota = updateQuota + paymentService.paymentFee(updateQuota);

            contract.getInstallments().add(new Installment(date, fullQuota));

        }
    }
}
