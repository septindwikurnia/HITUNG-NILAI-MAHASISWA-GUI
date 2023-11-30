package com.septinnia.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class main extends JFrame {
    private JTextField txtnim;
    private JTextField txtnama;
    private JTextField txtabsen;
    private JTextField txttugas;
    private JTextField outnim;
    private JTextField outnama;
    private JTextField outakhir;
    private JTextField outgrade;
    private JTextField outket;
    private JTextField txtuts;
    private JTextField txtuas;
    private JButton SUBMITButton;
    private JButton PRINTButton;
    private JButton CLEARButton;
    private JButton EXITButton;
    private JPanel panelmain;

    public main() {
        this.setContentPane(panelmain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtnim.getText().equals("") || txtnama.getText().equals("") ||
                        txtabsen.getText().equals("") || txttugas.getText().equals("") ||
                        txtuts.getText().equals("") || txtuas.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua kolom!");
                    return; // Hentikan eksekusi jika ada yang kosong
                }

                if (!txtnim.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "NIM hanya boleh berisi angka!");
                    return; // Hentikan eksekusi jika NIM tidak berisi angka
                }

                if (!txtnama.getText().matches("^[a-zA-Z ]+$")) {
                    JOptionPane.showMessageDialog(null, "Nama hanya boleh mengandung huruf!");
                    return; // Hentikan eksekusi jika nama mengandung angka atau karakter khusus
                }

                // Pemeriksaan bahwa input absen, tugas, UTS, dan UAS hanya berisi angka
                if (!txtabsen.getText().matches("\\d+") ||
                        !txttugas.getText().matches("\\d+") ||
                        !txtuts.getText().matches("\\d+") ||
                        !txtuas.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Absen, tugas, UTS, dan UAS harus berisi angka!");
                    return; // Hentikan eksekusi jika ada yang tidak berisi angka
                }

                String nim1 = txtnim.getText();
                String nama1 = txtnama.getText();
                String absen1 = txtabsen.getText();
                String tugas1 = txttugas.getText();
                String uts1 = txtuts.getText();
                String uas1 = txtuas.getText();

                long nim = Long.parseLong(txtnim.getText());
                int absen = Integer.parseInt(txtabsen.getText());
                int tugas = Integer.parseInt(txttugas.getText());
                int uts = Integer.parseInt(txtuts.getText());
                int uas = Integer.parseInt(txtuas.getText());


                String grade = null;
                String ket = null;

                if (absen >= 0 && absen <= 100 && tugas >= 0 && tugas <= 100 && uts >= 0 && uts <= 100
                        && uas >= 0 && uas <= 100) {
                    double nilai_akhir = ((absen * 0.1) + (tugas * 0.25) + (uts * 0.3) + (uas * 0.35));

                    if (nilai_akhir >= 90 && nilai_akhir <= 100) {
                        grade = "A";
                        ket = "Anda Lulus";
                    } else if (nilai_akhir >= 80 && nilai_akhir <= 89) {
                        grade = "B";
                        ket = "Anda Lulus";
                    } else if (nilai_akhir >= 70 && nilai_akhir <= 79) {
                        grade = "C";
                        ket = "Anda Lulus";
                    } else {
                        grade = "D";
                        ket = "Anda Tidak Lulus";
                    }
                    outnim.setText(nim1);
                    outnama.setText(nama1);
                    outakhir.setText(String.valueOf(nilai_akhir));
                    outgrade.setText(grade);
                    outket.setText(ket);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Nilai tidak diantara 0-100");
                }
            }
        });

        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtnim.setText("");
                txtnama.setText("");
                txtabsen.setText("");
                txttugas.setText("");
                txtuts.setText("");
                txtuas.setText("");
                outnim.setText("");
                outnama.setText("");
                outakhir.setText("");
                outgrade.setText("");
                outket.setText("");
            }
        });

        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int replay = JOptionPane.showConfirmDialog(null,
                        "Yakin mau keluar",
                        "Konfirmasi keluar aplikasi",
                        JOptionPane.YES_NO_OPTION);
                if (replay == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        PRINTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (isOutputNotEmpty()) {
                    try {
                        BufferedWriter obj = new BufferedWriter(new FileWriter("Hasil.txt"));
                        String hasil = "NIM : " + outnim.getText() + '\n' + "NAMA : " + outnama.getText() + '\n' +
                                "NILAI AKHIR : " + outakhir.getText() + '\n' + "GRADE : " + outgrade.getText() + '\n' +
                                "KETERANGAN : " + outket.getText();
                        obj.write((hasil));
                        JOptionPane.showMessageDialog(null, "Berhasil disimpan");
                        obj.close();
                    } catch (Exception e) {
                        System.out.println("Eror : " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak dapat menyimpan. Pastikan semua output diisi.");
                }
            }

            private boolean isOutputNotEmpty() {
                return !outnim.getText().isEmpty() &&
                        !outnama.getText().isEmpty() &&
                        !outakhir.getText().isEmpty() &&
                        !outgrade.getText().isEmpty() &&
                        !outket.getText().isEmpty();
            }
        });
    }

        public static void main (String[]args){
            main obj = new main();
            obj.setVisible(true);
        }
    }


