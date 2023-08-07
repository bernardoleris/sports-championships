package br.ufla.gac106.s2023_1.base;

import br.ufla.gac106.s2023_1.base.moduloAdministracao.InterfaceUsuario;

public class App {
    public static void main(String[] args) throws Exception {
        InterfaceUsuario iu = new InterfaceUsuario();
        iu.executar();
    }
}
