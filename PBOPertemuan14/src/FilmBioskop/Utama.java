/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FilmBioskop;

/**
 *
 * @author RIYANA
 */
import Akun.Loginn;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.persistence.*;

public class Utama extends javax.swing.JFrame {

    private DefaultTableModel model;
    private String username;
    Connection conn;
    Statement stmt;
    PreparedStatement pstmt = null;

    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/PBO_Ke12";
    String user = "postgres";
    String password = "10032020";
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     * Creates new form Utama
     */
    public Utama() {
        initComponents();
        showTableFilm();
        showTableJadwal();
    }

    public Utama(String username) {
        this.username = username; // simpan username untuk dipakai di seluruh class
        initComponents();
        showTableFilm();
        showTableJadwal();
        showTableStudio();
    }

    public void showTableFilm() {
        EntityManager em = Persistence.createEntityManagerFactory("PBO_Pertemuan12PU").createEntityManager();
        Query q = em.createNamedQuery("Film.findAll");
        List<Film> hasil = q.getResultList();

        DefaultTableModel modelFilm = new DefaultTableModel(new String[]{"Id Film", "Judul", "Genre", "Durasi"}, 0);
        for (Film f : hasil) {
            modelFilm.addRow(new Object[]{f.getIdFilm(), f.getJudul(), f.getGenre(), f.getDurasi()});
        }
        jTblFilm.setModel(modelFilm);
    }

    public void showTableStudio() {
        EntityManager em = Persistence.createEntityManagerFactory("PBO_Pertemuan12PU").createEntityManager();
        Query q = em.createNamedQuery("Studio.findAll");
        List<Studio> hasil = q.getResultList();

        DefaultTableModel modelStudio = new DefaultTableModel(new String[]{"Id Studio", "Studio"}, 0);
        for (Studio s : hasil) {
//            modelStudio.addRow(new Object[]{s.getIdStudio(), s.getNamaStudio()});
            modelStudio.addRow(new Object[]{
                s.getIdStudio(),
                s.getNamaStudio()
            });
        }
        jTblStudio.setModel(modelStudio);
        em.close(); // jangan lupa tutup EntityManager
    }

    public void showTableJadwal() {
        EntityManager em = Persistence.createEntityManagerFactory("PBO_Pertemuan12PU").createEntityManager();

        try {
            // Ambil semua jadwal
            Query q = em.createNamedQuery("JadwalTayang.findAll");
            List<JadwalTayang> hasil = q.getResultList();

            // Buat model tabel
            DefaultTableModel modelJadwal = new DefaultTableModel(
                    new String[]{"Id Jadwal", "Id Film", "Studio", "Tanggal Tayang", "Waktu Tayang"}, 0);

            // Format tanggal & waktu
            SimpleDateFormat sdfTanggal = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfWaktu = new SimpleDateFormat("HH:mm:ss");

            for (JadwalTayang j : hasil) {
                // Format tanggal & waktu
                String tanggalFormatted = j.getTanggalTayang() != null ? sdfTanggal.format(j.getTanggalTayang()) : "";
                String waktuFormatted = j.getWaktuTayang() != null ? sdfWaktu.format(j.getWaktuTayang()) : "";

                // Ambil ID Film
                String idFilm = (j.getIdFilm() != null) ? j.getIdFilm().getIdFilm() : "";

                // Ambil nama studio berdasarkan ID
                String namaStudio = "";
                if (j.getIdStudio() != null && !j.getIdStudio().isEmpty()) {
                    Studio studio = em.find(Studio.class, j.getIdStudio());
                    if (studio != null) {
                        namaStudio = studio.getNamaStudio();
                    }
                }

                // Tambahkan row ke tabel
                modelJadwal.addRow(new Object[]{
                    j.getIdJadwal(),
                    j.getIdFilm().getJudul(),
                    namaStudio,
                    tanggalFormatted,
                    waktuFormatted
                });
            }

            jTblJadwal.setModel(modelJadwal);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menampilkan tabel: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jBtnInsertFilm = new javax.swing.JButton();
        jBtnUpdateFilm = new javax.swing.JButton();
        jBtnDeleteFilm = new javax.swing.JButton();
        jBtnUploadFilm = new javax.swing.JButton();
        jBtnCetakFilm = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTblFilm = new javax.swing.JTable();
        jBtnDownloadFilm = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jBtnInsertStudio = new javax.swing.JButton();
        jBtnUpdateStudio = new javax.swing.JButton();
        jBtnDeleteStudio = new javax.swing.JButton();
        jBtnDownloadStudio = new javax.swing.JButton();
        jBtnUploadStudio = new javax.swing.JButton();
        jBtnCetakStudio = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblStudio = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblJadwal = new javax.swing.JTable();
        jBtnInsertJadwal = new javax.swing.JButton();
        jBtnUpdateJadwal = new javax.swing.JButton();
        jBtnDeleteJadwal = new javax.swing.JButton();
        jBtnUploadJadwal = new javax.swing.JButton();
        jBtnCetakJadwal = new javax.swing.JButton();
        jBtnDownloadJadwal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("FILM BIOSKOP");

        jTabbedPane1.setBackground(new java.awt.Color(255, 230, 245));

        jPanel2.setBackground(new java.awt.Color(255, 230, 245));

        jBtnInsertFilm.setBackground(new java.awt.Color(204, 0, 102));
        jBtnInsertFilm.setForeground(new java.awt.Color(255, 255, 255));
        jBtnInsertFilm.setText("Insert");
        jBtnInsertFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInsertFilmActionPerformed(evt);
            }
        });

        jBtnUpdateFilm.setBackground(new java.awt.Color(204, 0, 102));
        jBtnUpdateFilm.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUpdateFilm.setText("Update");
        jBtnUpdateFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdateFilmActionPerformed(evt);
            }
        });

        jBtnDeleteFilm.setBackground(new java.awt.Color(204, 0, 102));
        jBtnDeleteFilm.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDeleteFilm.setText("Delete");
        jBtnDeleteFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteFilmActionPerformed(evt);
            }
        });

        jBtnUploadFilm.setBackground(new java.awt.Color(204, 0, 102));
        jBtnUploadFilm.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUploadFilm.setText("Upload");
        jBtnUploadFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUploadFilmActionPerformed(evt);
            }
        });

        jBtnCetakFilm.setBackground(new java.awt.Color(204, 0, 102));
        jBtnCetakFilm.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCetakFilm.setText("Cetak");
        jBtnCetakFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCetakFilmActionPerformed(evt);
            }
        });

        jTblFilm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Film", "Judul", "Genre", "Durasi"
            }
        ));
        jScrollPane5.setViewportView(jTblFilm);

        jBtnDownloadFilm.setBackground(new java.awt.Color(204, 0, 102));
        jBtnDownloadFilm.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDownloadFilm.setText("Download");
        jBtnDownloadFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDownloadFilmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBtnInsertFilm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnUpdateFilm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnDeleteFilm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jBtnDownloadFilm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnUploadFilm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnCetakFilm))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtnCetakFilm, jBtnDeleteFilm, jBtnDownloadFilm, jBtnInsertFilm, jBtnUpdateFilm, jBtnUploadFilm});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnUpdateFilm)
                    .addComponent(jBtnDeleteFilm)
                    .addComponent(jBtnUploadFilm)
                    .addComponent(jBtnCetakFilm)
                    .addComponent(jBtnInsertFilm)
                    .addComponent(jBtnDownloadFilm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtnCetakFilm, jBtnDeleteFilm, jBtnDownloadFilm, jBtnInsertFilm, jBtnUpdateFilm, jBtnUploadFilm});

        jTabbedPane1.addTab("Film", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 230, 245));

        jBtnInsertStudio.setBackground(new java.awt.Color(204, 0, 102));
        jBtnInsertStudio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnInsertStudio.setText("Insert");
        jBtnInsertStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInsertStudioActionPerformed(evt);
            }
        });

        jBtnUpdateStudio.setBackground(new java.awt.Color(204, 0, 102));
        jBtnUpdateStudio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUpdateStudio.setText("Update");
        jBtnUpdateStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdateStudioActionPerformed(evt);
            }
        });

        jBtnDeleteStudio.setBackground(new java.awt.Color(204, 0, 102));
        jBtnDeleteStudio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDeleteStudio.setText("Delete");
        jBtnDeleteStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteStudioActionPerformed(evt);
            }
        });

        jBtnDownloadStudio.setBackground(new java.awt.Color(204, 0, 102));
        jBtnDownloadStudio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDownloadStudio.setText("Download");
        jBtnDownloadStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDownloadStudioActionPerformed(evt);
            }
        });

        jBtnUploadStudio.setBackground(new java.awt.Color(204, 0, 102));
        jBtnUploadStudio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUploadStudio.setText("Upload");
        jBtnUploadStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUploadStudioActionPerformed(evt);
            }
        });

        jBtnCetakStudio.setBackground(new java.awt.Color(204, 0, 102));
        jBtnCetakStudio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCetakStudio.setText("Cetak");
        jBtnCetakStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCetakStudioActionPerformed(evt);
            }
        });

        jTblStudio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Studio", "Studio"
            }
        ));
        jScrollPane2.setViewportView(jTblStudio);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jBtnInsertStudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnUpdateStudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnDeleteStudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jBtnDownloadStudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnUploadStudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnCetakStudio)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtnCetakStudio, jBtnDeleteStudio, jBtnDownloadStudio, jBtnInsertStudio, jBtnUpdateStudio, jBtnUploadStudio});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnInsertStudio)
                    .addComponent(jBtnUpdateStudio)
                    .addComponent(jBtnDeleteStudio)
                    .addComponent(jBtnUploadStudio)
                    .addComponent(jBtnCetakStudio)
                    .addComponent(jBtnDownloadStudio))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtnCetakStudio, jBtnDeleteStudio, jBtnDownloadStudio, jBtnInsertStudio, jBtnUpdateStudio, jBtnUploadStudio});

        jTabbedPane1.addTab("Studio", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 230, 245));
        jPanel3.setPreferredSize(new java.awt.Dimension(536, 257));

        jTblJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Jadwal", "Studio", "Tanggal Tayang", "Waktu Tayang", "Judul Film"
            }
        ));
        jScrollPane4.setViewportView(jTblJadwal);

        jBtnInsertJadwal.setBackground(new java.awt.Color(204, 0, 102));
        jBtnInsertJadwal.setForeground(new java.awt.Color(255, 255, 255));
        jBtnInsertJadwal.setText("Insert");
        jBtnInsertJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInsertJadwalActionPerformed(evt);
            }
        });

        jBtnUpdateJadwal.setBackground(new java.awt.Color(204, 0, 102));
        jBtnUpdateJadwal.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUpdateJadwal.setText("Update");
        jBtnUpdateJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdateJadwalActionPerformed(evt);
            }
        });

        jBtnDeleteJadwal.setBackground(new java.awt.Color(204, 0, 102));
        jBtnDeleteJadwal.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDeleteJadwal.setText("Delete");
        jBtnDeleteJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteJadwalActionPerformed(evt);
            }
        });

        jBtnUploadJadwal.setBackground(new java.awt.Color(204, 0, 102));
        jBtnUploadJadwal.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUploadJadwal.setText("Upload");
        jBtnUploadJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUploadJadwalActionPerformed(evt);
            }
        });

        jBtnCetakJadwal.setBackground(new java.awt.Color(204, 0, 102));
        jBtnCetakJadwal.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCetakJadwal.setText("Cetak");
        jBtnCetakJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCetakJadwalActionPerformed(evt);
            }
        });

        jBtnDownloadJadwal.setBackground(new java.awt.Color(204, 0, 102));
        jBtnDownloadJadwal.setForeground(new java.awt.Color(255, 255, 255));
        jBtnDownloadJadwal.setText("Download");
        jBtnDownloadJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDownloadJadwalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnInsertJadwal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnUpdateJadwal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnDeleteJadwal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jBtnDownloadJadwal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnUploadJadwal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnCetakJadwal)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtnCetakJadwal, jBtnDeleteJadwal, jBtnDownloadJadwal, jBtnInsertJadwal, jBtnUpdateJadwal, jBtnUploadJadwal});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnUploadJadwal)
                        .addComponent(jBtnCetakJadwal)
                        .addComponent(jBtnDownloadJadwal))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnInsertJadwal)
                        .addComponent(jBtnUpdateJadwal)
                        .addComponent(jBtnDeleteJadwal)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtnCetakJadwal, jBtnDeleteJadwal, jBtnDownloadJadwal, jBtnInsertJadwal, jBtnUpdateJadwal, jBtnUploadJadwal});

        jTabbedPane1.addTab("Jadwal Tayang", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnInsertJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInsertJadwalActionPerformed
        InsertDlgJadwal dialog = new InsertDlgJadwal(this, true, this); // tambahkan 'this' untuk kirim referensi FilmBioskop
        dialog.setVisible(true);
    }//GEN-LAST:event_jBtnInsertJadwalActionPerformed

    private void jBtnInsertFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInsertFilmActionPerformed
        InsertDlgFilm dialog = new InsertDlgFilm(this, true, this); // tambahkan 'this' untuk kirim referensi FilmBioskop
        dialog.setVisible(true);
    }//GEN-LAST:event_jBtnInsertFilmActionPerformed

    private void jBtnUpdateFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateFilmActionPerformed
        int row = jTblFilm.getSelectedRow();
        if (row != -1) {
            // ambil data dari tabel
            String id = jTblFilm.getValueAt(row, 0).toString();
            String judul = jTblFilm.getValueAt(row, 1).toString();
            String genre = jTblFilm.getValueAt(row, 2).toString();
            String durasi = jTblFilm.getValueAt(row, 3).toString();

            // buka dialog update
            UpdateDlgFilm dialog = new UpdateDlgFilm(this, true, this, id, judul, genre, durasi);
            dialog.setVisible(true);

            // reload tabel setelah update
            if (dialog.isUpdated()) {
                showTableFilm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau diupdate dulu!");
        }
    }//GEN-LAST:event_jBtnUpdateFilmActionPerformed

    private void jBtnUpdateJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateJadwalActionPerformed
        int row = jTblJadwal.getSelectedRow();
        if (row != -1) {
            // ambil data dari tabel
            String idJadwal = jTblJadwal.getValueAt(row, 0).toString();
            String idFilm = jTblJadwal.getValueAt(row, 1).toString();
            String idStudio = jTblJadwal.getValueAt(row, 2).toString();
            String tanggal = jTblJadwal.getValueAt(row, 3).toString();
            String waktu = jTblJadwal.getValueAt(row, 4).toString();

            // buka dialog update
            UpdateDlgJadwal dialog = new UpdateDlgJadwal(this, true, this, idJadwal, idFilm, idStudio, tanggal, waktu);
            dialog.setVisible(true);

            // reload tabel setelah update
            if (dialog.isUpdated()) {
                showTableFilm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau diupdate dulu!");
        }
    }//GEN-LAST:event_jBtnUpdateJadwalActionPerformed

    private void jBtnDeleteFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteFilmActionPerformed
        int row = jTblFilm.getSelectedRow();
        if (row >= 0) {
            // Ambil data dari tabel
            String id = jTblFilm.getValueAt(row, 0).toString();
            String judul = jTblFilm.getValueAt(row, 1).toString();
            String genre = jTblFilm.getValueAt(row, 2).toString();
            String durasi = jTblFilm.getValueAt(row, 3).toString();

            // Panggil dialog, kirim form ini sebagai parent
            DeleteDlgFilm dialog = new DeleteDlgFilm(this, true, this, id, judul, genre, durasi);
            dialog.setVisible(true);

            // Kalau user benar-benar delete, refresh tabel
            if (dialog.isDeleted()) {
                showTableFilm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data dulu dari tabel!");
        }
    }//GEN-LAST:event_jBtnDeleteFilmActionPerformed

    private void jBtnDeleteJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteJadwalActionPerformed
        int row = jTblJadwal.getSelectedRow();
        if (row >= 0) {
            // Ambil data dari tabel
            String idJadwal = jTblJadwal.getValueAt(row, 0).toString();
            String idFilm = jTblJadwal.getValueAt(row, 1).toString();
            String idStudio = jTblJadwal.getValueAt(row, 2).toString();
            String tanggal = jTblJadwal.getValueAt(row, 3).toString();
            String waktu = jTblJadwal.getValueAt(row, 4).toString();

            // Panggil dialog, kirim form ini sebagai parent
            DeleteDlgJadwal dialog = new DeleteDlgJadwal(this, true, this, idJadwal, idFilm, idStudio, tanggal, waktu);
            dialog.setVisible(true);

            // Kalau user benar-benar delete, refresh tabel
            if (dialog.isDeleted()) {
                showTableJadwal();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data dulu dari tabel!");
        }
    }//GEN-LAST:event_jBtnDeleteJadwalActionPerformed

    private void jBtnCetakFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCetakFilmActionPerformed
        JasperReport reports;

        String path = ".\\src\\FilmBioskop\\reportFilm.jasper";
        try {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(koneksi, user, password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            }
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map<String, Object> param = new HashMap<>();
            param.put("username", username); // kirim ke report
            JasperPrint jprint = JasperFillManager.fillReport(path, param, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnCetakFilmActionPerformed

    private void jBtnCetakJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCetakJadwalActionPerformed
        JasperReport reports;

        String path = ".\\src\\FilmBioskop\\reportJadwal.jasper";
        try {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(koneksi, user, password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            }
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map<String, Object> param = new HashMap<>();
            param.put("username", username); // kirim ke report
            JasperPrint jprint = JasperFillManager.fillReport(path, param, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnCetakJadwalActionPerformed

    private void jBtnUploadFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUploadFilmActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File filePilihan = jfc.getSelectedFile();
            System.out.println("yang dipilih : " + filePilihan.getAbsolutePath());

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PBO_Pertemuan12PU");
            EntityManager em = emf.createEntityManager();

            int jumlahBerhasil = 0;
            int jumlahDuplikat = 0;
            int jumlahGagal = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(filePilihan))) {
                String baris;
                String pemisah = ";";

                em.getTransaction().begin();

                while ((baris = br.readLine()) != null) {
                    String[] data = baris.split(pemisah);

                    if (data.length < 4) {
                        jumlahGagal++;
                        continue;
                    }

                    String idFilm = data[0].trim();
                    String judul = data[1].trim();
                    String genre = data[2].trim();
                    String durasiStr = data[3].trim();

                    if (idFilm.isEmpty() || judul.isEmpty() || genre.isEmpty()) {
                        jumlahGagal++;
                        continue;
                    }

                    int durasi;
                    try {
                        durasi = Integer.parseInt(durasiStr);
                    } catch (NumberFormatException ex) {
                        jumlahGagal++;
                        continue;
                    }

                    Film cek = em.find(Film.class, idFilm);
                    if (cek != null) {
                        jumlahDuplikat++;
                        continue;
                    }

                    Film f = new Film();
                    f.setIdFilm(idFilm);
                    f.setJudul(judul);
                    f.setGenre(genre);
                    f.setDurasi(durasi);

                    em.persist(f);
                    jumlahBerhasil++;
                }

                em.getTransaction().commit();
                showTableFilm();

                JOptionPane.showMessageDialog(null,
                        "Proses selesai!\n"
                        + "Berhasil: " + jumlahBerhasil + "\n"
                        + "Duplikat: " + jumlahDuplikat + "\n"
                        + "Data Error: " + jumlahGagal,
                        "Hasil Upload",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Kesalahan saat upload");
            } finally {
                em.close();
                emf.close();
            }
        }
    }//GEN-LAST:event_jBtnUploadFilmActionPerformed

    private void jBtnUploadJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUploadJadwalActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File filePilihan = jfc.getSelectedFile();
            System.out.println("yang dipilih : " + filePilihan.getAbsolutePath());

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PBO_Pertemuan12PU");
            EntityManager em = emf.createEntityManager();

            int jumlahBerhasil = 0;
            int jumlahDuplikat = 0;
            int jumlahFilmTidakAda = 0;
            int jumlahStudioTidakAda = 0;
            int jumlahFormatSalah = 0;
            int jumlahKosong = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(filePilihan))) {
                String baris;
                String pemisah = ";";

                em.getTransaction().begin();

                while ((baris = br.readLine()) != null) {
                    String[] data = baris.split(pemisah);

                    if (data.length < 5) {
                        jumlahKosong++;
                        continue;
                    }

                    String idJadwal = data[0].trim();
                    String judulFilm = data[1].trim();
                    String idStudio = data[2].trim();
                    String tanggalStr = data[3].trim();
                    String waktuStr = data[4].trim();

                    if (idJadwal.isEmpty() || judulFilm.isEmpty() || idStudio.isEmpty()
                            || tanggalStr.isEmpty() || waktuStr.isEmpty()) {
                        jumlahKosong++;
                        continue;
                    }

                    // Cek duplikat ID Jadwal
                    JadwalTayang cek = em.find(JadwalTayang.class, idJadwal);
                    if (cek != null) {
                        jumlahDuplikat++;
                        continue;
                    }

                    SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatWaktu = new SimpleDateFormat("HH:mm");

                    Date tanggalTayang;
                    Date waktuTayang;

                    try {
                        tanggalTayang = formatTanggal.parse(tanggalStr);
                        waktuTayang = formatWaktu.parse(waktuStr);
                    } catch (ParseException e) {
                        jumlahFormatSalah++;
                        continue;
                    }

                    // Cari Film berdasarkan judul
                    Film f;
                    try {
                        Query qFilm = em.createQuery("SELECT f FROM Film f WHERE f.judul = :judul");
                        qFilm.setParameter("judul", judulFilm);
                        f = (Film) qFilm.getSingleResult();
                    } catch (NoResultException e) {
                        jumlahFilmTidakAda++;
                        continue;
                    }

                    // Cari Studio berdasarkan namaStudio
                    Studio s;
                    try {
                        Query qStudio = em.createQuery("SELECT s FROM Studio s WHERE s.namaStudio = :nama");
                        qStudio.setParameter("nama", idStudio);
                        s = (Studio) qStudio.getSingleResult();
                    } catch (NoResultException e) {
                        jumlahStudioTidakAda++;
                        continue;
                    }

                    JadwalTayang j = new JadwalTayang();
                    j.setIdJadwal(idJadwal);
                    j.setIdStudio(s.getIdStudio()); // <-- sudah object Studio
                    j.setTanggalTayang(tanggalTayang);
                    j.setWaktuTayang(waktuTayang);
                    j.setIdFilm(f);

                    em.persist(j);
                    jumlahBerhasil++;
                }

                em.getTransaction().commit();
                showTableJadwal();

                JOptionPane.showMessageDialog(null,
                        "Proses Selesai!\n"
                        + "Berhasil: " + jumlahBerhasil + "\n"
                        + "Duplikat ID Jadwal: " + jumlahDuplikat + "\n"
                        + "Film Tidak Ada: " + jumlahFilmTidakAda + "\n"
                        + "Studio Tidak Ada: " + jumlahStudioTidakAda + "\n"
                        + "Format Salah (Tanggal/Waktu): " + jumlahFormatSalah + "\n"
                        + "Data Kosong/Tidak Lengkap: " + jumlahKosong,
                        "Hasil Upload",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat upload");
            } finally {
                em.close();
                emf.close();
            }
        }
    }//GEN-LAST:event_jBtnUploadJadwalActionPerformed

    private void jBtnDownloadFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDownloadFilmActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan sebagai CSV");

        // Nama default
        fileChooser.setSelectedFile(new File("Data_Film.csv"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileChooser.getSelectedFile();

            // Cek jika file sudah ada --> konfirmasi ke user
            if (fileToSave.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "File sudah ada.\nApakah Anda ingin menimpanya?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return; // batalkan
                }
            }

            try (FileWriter fw = new FileWriter(fileToSave)) {

                DefaultTableModel model = (DefaultTableModel) jTblFilm.getModel();
                int colCount = model.getColumnCount();
                int rowCount = model.getRowCount();

                // Tulis isi tabel
                for (int r = 0; r < rowCount; r++) {
                    for (int c = 0; c < colCount; c++) {
                        Object value = model.getValueAt(r, c);
                        String cell = (value == null) ? "" : value.toString().replace(";", ",");
                        fw.write(cell);
                        if (c < colCount - 1) {
                            fw.write(";");
                        }
                    }
                    fw.write("\n");
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil diekspor ke file CSV!\n" + fileToSave.getAbsolutePath(),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Terjadi kesalahan saat menyimpan:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jBtnDownloadFilmActionPerformed

    private void jBtnDownloadJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDownloadJadwalActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan sebagai CSV");

        // Nama default
        fileChooser.setSelectedFile(new File("Data_JadwalTayang.csv"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileChooser.getSelectedFile();

            // Cek jika file sudah ada --> konfirmasi ke user
            if (fileToSave.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "File sudah ada.\nApakah Anda ingin menimpanya?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return; // batalkan
                }
            }

            try (FileWriter fw = new FileWriter(fileToSave)) {

                DefaultTableModel model = (DefaultTableModel) jTblJadwal.getModel();
                int colCount = model.getColumnCount();
                int rowCount = model.getRowCount();

                // Tulis isi tabel
                for (int r = 0; r < rowCount; r++) {
                    for (int c = 0; c < colCount; c++) {
                        Object value = model.getValueAt(r, c);
                        String cell = (value == null) ? "" : value.toString().replace(";", ",");
                        fw.write(cell);
                        if (c < colCount - 1) {
                            fw.write(";");
                        }
                    }
                    fw.write("\n");
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil diekspor ke file CSV!\n" + fileToSave.getAbsolutePath(),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Terjadi kesalahan saat menyimpan:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jBtnDownloadJadwalActionPerformed

    private void jBtnInsertStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInsertStudioActionPerformed
        InsertDlgStudio dialog = new InsertDlgStudio(this, true, this); // tambahkan 'this' untuk kirim referensi FilmBioskop
        dialog.setVisible(true);
    }//GEN-LAST:event_jBtnInsertStudioActionPerformed

    private void jBtnUpdateStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateStudioActionPerformed
        int row = jTblStudio.getSelectedRow();
        if (row != -1) {
            // Ambil data dari tabel
            String idStudio = jTblStudio.getValueAt(row, 0).toString();
            String studio = jTblStudio.getValueAt(row, 1).toString();

            // Buka dialog update
            UpdateDlgStudio dialog = new UpdateDlgStudio(this, true, this, idStudio, studio);
            dialog.setVisible(true);

            // Reload tabel setelah update
            if (dialog.isUpdated()) {
                showTableStudio();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data studio yang mau diupdate dulu!");
        }
    }//GEN-LAST:event_jBtnUpdateStudioActionPerformed

    private void jBtnDeleteStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteStudioActionPerformed
        int row = jTblStudio.getSelectedRow();
        if (row >= 0) {
            // Ambil data dari tabel
            String idStudio = jTblStudio.getValueAt(row, 0).toString();
            String Studio = jTblStudio.getValueAt(row, 1).toString();

            // Panggil dialog delete, kirim parent frame + data studio
            DeleteDlgStudio dialog = new DeleteDlgStudio(this, true, this, idStudio, Studio);
            dialog.setVisible(true);

            // Kalau user benar-benar delete, refresh tabel
            if (dialog.isDeleted()) {
                showTableStudio();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data studio dulu dari tabel!");
        }
    }//GEN-LAST:event_jBtnDeleteStudioActionPerformed

    private void jBtnDownloadStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDownloadStudioActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan sebagai CSV");

// Nama default
        fileChooser.setSelectedFile(new File("Data_Studio.csv"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileChooser.getSelectedFile();

            // Cek jika file sudah ada --> konfirmasi ke user
            if (fileToSave.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "File sudah ada.\nApakah Anda ingin menimpanya?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return; // batalkan
                }
            }

            try (FileWriter fw = new FileWriter(fileToSave)) {

                DefaultTableModel model = (DefaultTableModel) jTblStudio.getModel();
                int colCount = model.getColumnCount();
                int rowCount = model.getRowCount();

                // Tulis isi tabel
                for (int r = 0; r < rowCount; r++) {
                    for (int c = 0; c < colCount; c++) {
                        Object value = model.getValueAt(r, c);
                        String cell = (value == null) ? "" : value.toString().replace(";", ",");
                        fw.write(cell);
                        if (c < colCount - 1) {
                            fw.write(";");
                        }
                    }
                    fw.write("\n");
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Data studio berhasil diekspor ke file CSV!\n" + fileToSave.getAbsolutePath(),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Terjadi kesalahan saat menyimpan:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jBtnDownloadStudioActionPerformed

    private void jBtnUploadStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUploadStudioActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File filePilihan = jfc.getSelectedFile();
            System.out.println("File dipilih: " + filePilihan.getAbsolutePath());

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PBO_Pertemuan12PU");
            EntityManager em = emf.createEntityManager();

            int jumlahBerhasil = 0;
            int jumlahDuplikat = 0;
            int jumlahGagal = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(filePilihan))) {
                String baris;
                String pemisah = ";";

                em.getTransaction().begin();

                while ((baris = br.readLine()) != null) {
                    String[] data = baris.split(pemisah);

                    // Kolom wajib tepat 2
                    if (data.length != 2) {
                        jumlahGagal++;
                        continue;
                    }

                    String idStudio = data[0].trim();
                    String namaStudio = data[1].trim();

                    if (idStudio.isEmpty() || namaStudio.isEmpty()) {
                        jumlahGagal++;
                        continue;
                    }

                    // Cek duplikat ID studio
                    Studio existing = em.find(Studio.class, idStudio);
                    if (existing != null) {
                        jumlahDuplikat++;
                        continue;
                    }

                    Studio s = new Studio();
                    s.setIdStudio(idStudio);
                    s.setNamaStudio(namaStudio);

                    em.persist(s);
                    jumlahBerhasil++;
                }

                em.getTransaction().commit();
                showTableStudio();

                JOptionPane.showMessageDialog(null,
                        "Proses Selesai!\n"
                        + "Berhasil: " + jumlahBerhasil + "\n"
                        + "Duplikat: " + jumlahDuplikat + "\n"
                        + "Data Error: " + jumlahGagal,
                        "Hasil Upload",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat upload data studio");
            } finally {
                em.close();
                emf.close();
            }
        }
    }//GEN-LAST:event_jBtnUploadStudioActionPerformed

    private void jBtnCetakStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCetakStudioActionPerformed
        JasperReport reports;

        String path = ".\\src\\FilmBioskop\\reportStudio.jasper"; // file .jasper Studio
        try {
            try {
                Class.forName(driver); // driver JDBC
                conn = DriverManager.getConnection(koneksi, user, password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
            }
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map<String, Object> param = new HashMap<>();
            param.put("username", username); // optional, jika ada parameter di report
            JasperPrint jprint = JasperFillManager.fillReport(path, param, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnCetakStudioActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Utama().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCetakFilm;
    private javax.swing.JButton jBtnCetakJadwal;
    private javax.swing.JButton jBtnCetakStudio;
    private javax.swing.JButton jBtnDeleteFilm;
    private javax.swing.JButton jBtnDeleteJadwal;
    private javax.swing.JButton jBtnDeleteStudio;
    private javax.swing.JButton jBtnDownloadFilm;
    private javax.swing.JButton jBtnDownloadJadwal;
    private javax.swing.JButton jBtnDownloadStudio;
    private javax.swing.JButton jBtnInsertFilm;
    private javax.swing.JButton jBtnInsertJadwal;
    private javax.swing.JButton jBtnInsertStudio;
    private javax.swing.JButton jBtnUpdateFilm;
    private javax.swing.JButton jBtnUpdateJadwal;
    private javax.swing.JButton jBtnUpdateStudio;
    private javax.swing.JButton jBtnUploadFilm;
    private javax.swing.JButton jBtnUploadJadwal;
    private javax.swing.JButton jBtnUploadStudio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTblFilm;
    private javax.swing.JTable jTblJadwal;
    private javax.swing.JTable jTblStudio;
    // End of variables declaration//GEN-END:variables
}
