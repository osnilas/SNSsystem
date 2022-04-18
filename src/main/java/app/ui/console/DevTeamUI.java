package app.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t João Veiga - 1201082@isep.ipp.pt \n");
        System.out.printf("\t Pedro Nogueira - 1211613@isep.ipp.pt \n");
        System.out.printf("\t Filipe Magalhães - 1211606@isep.ipp.pt \n");
        System.out.printf("\t Afonso Cunha - 1190325@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
