package com.example.vendingmachine.vendingmachine;

import android.os.AsyncTask;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.vendingmachine.Student;
import com.example.vendingmachine.vendingmachine.products.IProduct;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class IVendingMachine implements Serializable {
    private final MutableLiveData<List<Student>>  queue;
    private final Map<IProduct, Integer> products;

    private ObservableField<String> status;
    private ObservableField<String> student;
    private ObservableInt amount;

    public IVendingMachine(List<Student> queue, Map<IProduct, Integer> products) {
        this.queue = new MutableLiveData<>();
        this.products = products;

        this.status=new ObservableField<>();
        this.student=new ObservableField<>();
        this.amount=new ObservableInt();

        this.queue.setValue(queue);
        this.status.set("Простаивает");
    }


    public void processClient(){
        if (queue.getValue().size()==0) {
            this.status.set("Простаивает");
            this.student.set("Пустота");
            this.amount.set(0);
            return;
        }
        ClientProcessing clientProcessing = new ClientProcessing();
        clientProcessing.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, queue.getValue().get(0));
    }

    class ClientProcessing extends AsyncTask<Student, ProgressUpdateRequest, String> {


        @Override
        protected void onPreExecute(){
            status.set("Принимает");
        }

        @Override
        protected String doInBackground(Student... students) {
            Student currentStudent = null;
            for (Student student: students) {
                currentStudent=student;
            }

            // обновление имени покупателя
            publishProgress(new ProgressUpdateRequest(currentStudent.getName()+"\n"+currentStudent.getId(), ProgressUpdateRequest.STUDENT_KEY));

            mySleep();
            //выбор продуктов
            Log.d("IVendingMachine", "Выбор продуктов");
            Map<IProduct, Integer> chosenProducts = choseProducts();
            // подсчёт суммы выбора
            Log.d("IVendingMachine", "Подсчёт суммы выбора");
            Integer sum = calculatePrice(chosenProducts);
            // обновление суммы закупки
            publishProgress(new ProgressUpdateRequest(sum, ProgressUpdateRequest.AMOUNT_KEY));
            mySleep();

            publishProgress(new ProgressUpdateRequest("Оплачивает", ProgressUpdateRequest.STATUS_KEY));
            mySleep();
            publishProgress(new ProgressUpdateRequest("Выдаёт", ProgressUpdateRequest.STATUS_KEY));
            mySleep();

            return "Простаивает";

        }

        @Override
        protected void onPostExecute(String result){
            List<Student> students = queue.getValue();
            students.remove(0);
            queue.setValue(students);

            status.set(result);
            amount.set(0);
            processClient();
        }

        @Override
        protected void onProgressUpdate( ProgressUpdateRequest ... values) {
            super.onProgressUpdate(values);

            ProgressUpdateRequest request=null;
            for (ProgressUpdateRequest value: values){
                request=value;
            }

            switch (request.key){
                case ProgressUpdateRequest.STATUS_KEY:
                    status.set((String) request.getValue());
                    break;
                case ProgressUpdateRequest.AMOUNT_KEY:
                    amount.set((Integer) request.getValue());
                    break;
                case ProgressUpdateRequest.STUDENT_KEY:
                    student.set((String) request.getValue());
                    break;
            }
        }

        private Map<IProduct, Integer> choseProducts(){
            Random random = new Random();
            HashMap<IProduct, Integer> result = new HashMap<>();
            for (IProduct product: products.keySet()){
                int currentCountOfProduct = products.get(product);
                if (currentCountOfProduct==0) continue;
                int numberOfChosenProduct = random.nextInt(currentCountOfProduct/4);
                // изменение количества доступных продуктов
                products.put(product, currentCountOfProduct-numberOfChosenProduct);

                result.put(product, numberOfChosenProduct);
            }

            return  result;
        }
        private Integer calculatePrice(Map<IProduct, Integer> products){
            Integer sum=0;
            for (IProduct product: products.keySet()){
                sum+=products.get(product)*product.getPrice();
            }

            return sum;
        }
        private void mySleep(){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ProgressUpdateRequest{
        private Object value;
        private String key;

        public final static String STATUS_KEY="status";
        public final static String AMOUNT_KEY ="amount";
        public final static String STUDENT_KEY="student";

        public ProgressUpdateRequest(Object value, String key){
            this.key = key;
            this.value=value;
        }

        public Object getValue() {
            return value;
        }
        public String getKey() {
            return key;
        }

    }

    public ObservableField<String> getStatus() {
        return status;
    }
    public ObservableField<String> getStudent() {
        return student;
    }
    public ObservableInt getAmount() {
        return amount;
    }
    public MutableLiveData<List<Student>> getQueue(){return queue;}
}