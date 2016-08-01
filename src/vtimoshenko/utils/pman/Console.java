package vtimoshenko.utils.pman;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SimpleUser on 14.07.2016.
 */
public class Console {

    PassMan pss;

    public void run(String[] args){
        pr("password manager.");
        pss = new PassMan();
        cycle();
    }

    public void cycle(){
        Scanner in = new Scanner(System.in);
        while (true){
            pr("\npMan: ");
            String str = in.nextLine();
            String[] argsa = str.split(" ");
            ArrayList<String> args = new ArrayList<>();
            for (String s : argsa) args.add(s);

            String cmd = args.get(0); args.remove(0);
            if      (cmd.equals("pass") || cmd.equals("p"))     pr(pass(args));
            else if (cmd.equals("c")    || cmd.equals("cd"))    pr(c(args));
            else if (cmd.equals("list") || cmd.equals("l"))     pr(list(args));
            else if (cmd.equals("debug"))                       pr(pss.debug());
            else if (cmd.equals(""))        continue;
            else if (cmd.equals("exit"))    break;
            else                                pr("Unknown command!");
        }
        pr("\nBye");
    }


    private String pass(ArrayList<String> args){
        ArrayList<String> parms = new ArrayList<>();
        boolean rewrite = false;
        boolean recursive = false;
        boolean withpass = false;
        for (String s : args){
           if       (s.equals("-rw"))   rewrite = true;
           else if  (s.equals("-r"))    recursive = true;
           else if  (s.equals("-p"))    withpass = true;
           else parms.add(s);
        }
        if (parms.size()==3){
            return pss.newPass(parms.get(0), parms.get(1), parms.get(2), rewrite);
        }
        if (parms.size()==1){
            if (test(parms.get(0), "^.+:.+@.+$")){
                String[] ar1 = parms.get(0).split("@");
                String[] ar2 = ar1[0].split(":");
                return pss.newPass(ar1[1], ar2[0], ar2[1], rewrite);
            }
            else if (test(parms.get(0), "^.+@.+$")){
                String[] ar1 = parms.get(0).split("@");
                return pss.getPass(ar1[1], ar1[0]);
            }
            else {
                return pss.listPass(parms.get(0), recursive, withpass);
            }
        }
        if (parms.size()==0)
            return pss.listPass("", recursive, withpass);
        return "invalid syntax";
    }

    private String c(ArrayList<String> args){
        if (args.size()>0) return pss.changeNode(args.get(0));
        else return pss.changeNode("");
    }

    private String list(ArrayList<String> args){
        ArrayList<String> parms = new ArrayList<>();
        boolean recursive = false;
        for (String s : args){
            if  (s.equals("-r"))    recursive = true;
            else parms.add(s);
        }
        if (parms.size()>0) return pss.listNodes(args.get(0), recursive);
        else return pss.listNodes("", recursive);
    }


    private boolean test(String test, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(test);
        return m.matches();
    }

    private void pr(String str){
        System.out.print(str);
    }
}
