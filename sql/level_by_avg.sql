
--查询某个班级的学生的成绩平均分，并按分数分等级

select student_id,
case when avgs>=90 then 'A'
     when avgs>=80 then 'B'
     when avgs>=70 then 'C'
     when avgs>=60 then 'D'
     else 'NO'
end as level from
(select student_id, avg(score) as avgs from scores where class_id=1 group by student_id) aa;