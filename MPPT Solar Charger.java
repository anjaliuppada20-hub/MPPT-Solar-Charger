# MPPT-Solar-Charger
Uses Maximum Power Point Tracking to extract more power from a solar panel.
import java.util.Scanner;

public class MPPTSolarCharger {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== MPPT SOLAR CHARGER =====");

        System.out.print("Enter Solar Voltage (V): ");
        double voltage = sc.nextDouble();

        System.out.print("Enter Solar Current (A): ");
        double current = sc.nextDouble();

        System.out.print("Enter Battery Voltage (V): ");
        double batteryVoltage = sc.nextDouble();

        // Solar power
        double solarPower = voltage * current;

        // MPPT calculation
        double dutyCycle = 50.0;
        double step = 5.0;

        double maxPower = solarPower;
        double bestVoltage = voltage;

        for (int i = 0; i < 10; i++) {

            double testVoltage = voltage * (0.8 + i * 0.02);
            double testCurrent = current * (1.0 - i * 0.03);

            double testPower = testVoltage * testCurrent;

            if (testPower > maxPower) {
                maxPower = testPower;
                bestVoltage = testVoltage;
                dutyCycle += step;
            } else {
                dutyCycle -= step;
            }

            dutyCycle = Math.max(10, Math.min(90, dutyCycle));
        }

        // Charging current
        double chargingCurrent = maxPower / batteryVoltage;

        System.out.println("\n===== MPPT RESULT =====");
        System.out.printf("Maximum Power   : %.2f W%n", maxPower);
        System.out.printf("MPPT Voltage    : %.2f V%n", bestVoltage);
        System.out.printf("Duty Cycle      : %.2f %% %n", dutyCycle);
        System.out.printf("Battery Voltage : %.2f V%n", batteryVoltage);
        System.out.printf("Charging Current: %.2f A%n", chargingCurrent);

        System.out.println("\nMPPT Charger Status: ACTIVE");

        sc.close();
    }
}
