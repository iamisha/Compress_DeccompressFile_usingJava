
import java.util.zip.*;
import java.io.*;

class ZipHelper {
    public void zip(File folder) {
        File[] files = folder.listFiles();
        if (files.length == 0) {
            throw new IllegalArgumentException("You're Folder is empty");
        }
        try {
            FileOutputStream fos = new FileOutputStream("zipfolder.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (File file : files) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);
                int data;
                while ((data = fis.read()) != -1) {
                    zos.write(data);
                }
                fis.close();
            }
            zos.closeEntry();
            zos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            new ZipHelper().zip(new File("java-2nd"));
            new ZipHelper().unzip(new File("zipfolder.zip"));

        } catch (IllegalArgumentException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void unzip(File filetounzip) {
        try {
            FileInputStream fis = new FileInputStream(filetounzip);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                File file = new File("newFiles/" + ze.getName());
                FileOutputStream fout = new FileOutputStream(file);
                int data;
                while ((data = zis.read()) != -1) {
                    fout.write(data);
                }
                fout.close();
                ze = zis.getNextEntry();
            }
            zis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}