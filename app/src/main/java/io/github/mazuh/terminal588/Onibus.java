package io.github.mazuh.terminal588;

import android.util.Log;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author mazuh
 */
public class Onibus implements Serializable {

    /* Dados do veículo */
    private Calendar horario;
    private String empresa;

    /* Nomes das empresas de ônibus */
    public static final String GUANABARA = "Guanabara";
    public static final String STMARIA   = "Santa Maria";
    public static final String REUNIDAS  = "Reunidas";
    public static final String CONCEICAO = "Conceição";
    public static final String VIASUL    = "Via Sul";
    public static final String CIDNATAL  = "Cidade Natal";

    /**
     * Construtor.
     *
     * @param empresa o nome da empresa a qual o ônibus deste horário partirá (usar as constantes!)
     * @param horario hora em que o busão parte no formato "hh:mm"
     */
    public Onibus(String empresa, String horario) {
        this.empresa = empresa;

        String[] dadosHorario = horario.split(":");
        this.horario = getCalendar(
                Integer.valueOf(dadosHorario[0]),
                Integer.valueOf(dadosHorario[1])
        );

    }


    /**
     * Produz string que representa verbalmente o objeto.
     *
     * @return representação do ônibus no formato "hh:mm - empresa"
     */
    @Override
    public String toString() {
        return getHorarioStr() + " - " + this.empresa;
    }


    /**
     * Calcula quantos minutos faltam pra o ônibus partir.
     *
     * @return inteiro de minutos até o horário deste ônibus partir;
     * ou um valor negativo caso já tenha passado;
     * ou 0 se o ônibus deve passar no minuto atual minuto
     */
    public int minutosParaPartir() {
        long diferencaMs = this.horario.compareTo(getCalendar());
        return ((int) (diferencaMs * 1000 * 60));
    }


    /**
     * Verifica se o ônibus já partiu do terminal hoje.
     *
     * @return true se o horário atual estiver depois do deste.Onibus
     */
    public boolean jaPartiu() {
        return getCalendar().after(this.horario);
    }

    /**
     * Verifica se este ônibus partiu depois de outro.
     *
     * @param  outroOnibus segundo objeto Ônibus para comparar com este
     *
     * @return true se o horário deste ônibus estiver após o horário do outroOnibus
     */
    public boolean passouDepoisDe(Onibus outroOnibus) {
        return this.horario.after(outroOnibus.getHorario());
    }





    /* PRIVATES */

    /**
     * Gera uma instância da classe Calendar com horário customizável para ser usada no sistema.
     *
     * @param  hora   hora no intervalo [0,24[
     * @param  minuto minuto no intervalo [0,60[
     *
     * @return objeto Calendar com timezone Brazil/East, s e ms em 0, e com hora e minuto
     * de acordo com os parâmetros
     */
    private Calendar getCalendar(int hora, int minuto) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hora));
        c.set(Calendar.MINUTE, Integer.valueOf(minuto));
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }


    /**
     * Gera uma instância da classe Calendar com horário atual para ser usada no sistema.
     *
     * @return objeto Calendar com timezone Brazil/East, s e ms em 0, e com hora e minuto
     * de acordo com o tempo atual.
     */
    private Calendar getCalendar() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    /**
     * Getter de horário no formato string "hh:mm"
     *
     * @return string do horário em formato "hh:mm"
     */
    public String getHorarioStr(){
        int hora = this.horario.get(Calendar.HOUR_OF_DAY);
        int minuto = this.horario.get(Calendar.MINUTE);

        return ((hora < 10 ? "0" : "") + String.valueOf(hora))
                + ":"
                + ((minuto < 10 ? "0" : "") + String.valueOf(minuto));
    }




    /* ACESSOS PADRÃO */

    public Calendar getHorario() {
        return this.horario;
    }

    public String getEmpresa() {
        return this.empresa;
    }

}
