package com.roadjava.service;

import com.roadjava.entity.StudentDO;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

import javax.swing.table.TableColumn;

public interface StudentService {
        TableDTO retriveStudents(StudentRequest request );

        boolean add(StudentDO studentDO);

        StudentDO getbyId(int selectedStudentId);

        boolean update(StudentDO studentDO);

        boolean delete(int[] selectedStudentIds);
}
