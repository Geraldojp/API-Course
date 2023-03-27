package com.example.belajar_spring.service;

import com.example.belajar_spring.exception.ExistedDataException;
import com.example.belajar_spring.exception.NotFoundException;
import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.model.CourseInfo;
import com.example.belajar_spring.model.CourseType;
import com.example.belajar_spring.model.request.CourseRequest;
import com.example.belajar_spring.repository.CourseInfoRepository;
import com.example.belajar_spring.repository.CourseRepository;
import com.example.belajar_spring.repository.CourseTypeRepository;
import com.example.belajar_spring.utils.specification.SearchCriteria;
import com.example.belajar_spring.utils.specification.Spec;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService<Course> {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private CourseTypeService courseTypeService;
    @Autowired
    private ModelMapper modelMapper;

    public Iterable<Course> findAll(Pageable pageable){
        try {
            Iterable<Course> find = courseRepository.findAll(pageable);
            if(find == null)
                throw new NotFoundException("Data not found");
            return find;
        }catch (NotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        return null;
    }

    public Course create(CourseRequest course){
        try {
            if(courseRepository.existsByTitleIgnoreCase(course.getTitle())){
                throw new ExistedDataException("Data already exist");
            }
            CourseInfo courseInfo = modelMapper.map(course, CourseInfo.class);
            CourseInfo setInfo = courseInfoService.create(courseInfo);
            Optional<CourseType> courseType = courseTypeService.findById(course.getCourseType().getCourseTypeId());
            Course newCourse = modelMapper.map(course, Course.class);
            System.out.println(newCourse.toString());
            newCourse.setCourseInfo(setInfo);
            newCourse.setCourseType(courseType.get());

            return courseRepository.save(newCourse);
        }catch (ExistedDataException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Course update(Course course, Long id){
        try {
            Optional<Course> find = courseRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException("Data not found");
            }
            if(courseRepository.existsByTitleIgnoreCase(course.getTitle())){
                throw new ExistedDataException("Data already exist");
            }
            course.setCourseId(id);
            return courseRepository.save(course);
        }catch (NotFoundException e){
            throw e;
        }
        catch (ExistedDataException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void deleteById(Long id){
        try {
            Optional<Course> find = courseRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException("Data not found");
            }
            courseRepository.deleteById(id);
        }catch (NotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Optional<Course> findById(Long id){
        try {
            Optional<Course> find = courseRepository.findById(id);
            if(find.isEmpty()){
                throw new NotFoundException("Data not found");
            }
            return find;
        }catch (NotFoundException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<Course> listBy(SearchCriteria searchCriteria) throws Exception{
        Specification specification = new Spec<Course>().findBy(searchCriteria);
        List<Course> courses = courseRepository.findAll(specification);
        return courses;
    }
}
