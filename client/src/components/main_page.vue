<template>
  <div class="my-div">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>{{id}}  {{name}}</span>
        <el-button style="float: right; padding: 5px 0px" type="primary" plain @click="addCAFormVisible = true">Add CourseArrangement</el-button>
        <el-button style="float: right; padding: 5px 0px; margin-right: 5px" type="primary" plain @click="addCourseFormVisible = true">Add Course</el-button>
      </div>
      <el-table
          :data="courseArrangementTable"
          style="width: 100%">
        <el-table-column
            prop="id"
            label="id"
            width="100">
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="course"
            width="180">
        </el-table-column>
        <el-table-column
            prop="teacherName"
            label="teacher"
            width="180">
        </el-table-column>
        <el-table-column
            prop="semester"
            label="semester"
            width="180">
        </el-table-column>
        <el-table-column
            prop="time"
            label="time"
            width="250">
        </el-table-column>
        <el-table-column label="Operation">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="danger"
                @click="chooseCourse(scope.$index, scope.row)">Choose</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-row>
      <el-col :span="12">
        <el-card class="course-card">
          <div slot="header">
            <span>Course Table</span>
          </div>
          <el-table
              :data="courseTable"
              style="width: 100%">
            <el-table-column
                prop="id"
                label="id"
                width="100">
            </el-table-column>
            <el-table-column
                prop="name"
                label="course"
                width="180">
            </el-table-column>
            <el-table-column
                prop="credit"
                label="credit"
                width="180">
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="teacher-card">
          <div slot="header">
            <span>Teacher Table</span>
          </div>
          <el-table
              :data="teacherTable"
              style="width: 100%">
            <el-table-column
                prop="id"
                label="id"
                width="100">
            </el-table-column>
            <el-table-column
                prop="name"
                label="name"
                width="180">
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-card class="selected-card">
      <div slot="header">
        <span>Select Table</span>
      </div>
      <el-table
          :data="selectTable"
          style="width: 100%">
        <el-table-column
            prop="id"
            label="id"
            width="100">
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="course"
            width="100">
        </el-table-column>
        <el-table-column
            prop="teacherName"
            label="teacher"
            width="100">
        </el-table-column>
        <el-table-column
            prop="score"
            label="score"
            width="180">
        </el-table-column>
        <el-table-column label="Operation">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="danger"
                @click="deleteSelectCourse(scope.$index, scope.row)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="Add CourseArrangement" :visible.sync="addCAFormVisible">
      <el-form :model="courseArrangementForm">
        <el-form-item label="Course CourseID" :label-width="formLabelWidth">
          <el-input v-model="courseArrangementForm.courseID" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course TeacherID" :label-width="formLabelWidth">
          <el-input v-model="courseArrangementForm.teacherID" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Semester" :label-width="formLabelWidth">
          <el-select v-model="courseArrangementForm.semester" placeholder="请选择">
            <el-option
                v-for="item in semesterList"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Course Time" :label-width="formLabelWidth">
          <el-time-select v-model="time1" style="width: 190px"
                          :picker-options="{start: '08:10', step: '00:15', end: '18:30'}">
          </el-time-select>
          <span> To </span>
          <el-time-select v-model="time2" style="width: 190px"
                          :picker-options="{start: '08:10', step: '00:15', end: '18:30'}">
          </el-time-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCAFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCourseArrangement">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="Add Course" :visible.sync="addCourseFormVisible">
      <el-form :model="courseForm">
        <el-form-item label="Course ID" :label-width="formLabelWidth">
          <el-input v-model="courseForm.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Name" :label-width="formLabelWidth">
          <el-input v-model="courseForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Credit" :label-width="formLabelWidth">
          <el-input v-model="courseForm.credit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCAFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCourse">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "main_page",
    data() {
      return {
        id: 10001,
        name: 'student1',
        selectTable: [],
        teacherTable: [],
        courseTable: [],
        courseArrangementTable: [],
        addCourseFormVisible: false,
        addCAFormVisible: false,
        formLabelWidth: '125px',
        time1: '',
        time2: '',
        courseForm: {
          id: '',
          name: '',
          credit: ''
        },
        courseArrangementForm: {
          courseID: '',
          teacherID: '',
          semester: '',
          time: ''
        },
        semesterList: ['First Semester', 'Second Semester']
      }
    },
    created() {
      this.getAllSelectData()
      this.getAllTeacherData()
      this.getAllCourseData()
      this.getCourseArrangementData()
    },
    methods: {
      addCourse() {
        this.addCourseFormVisible = false
        axios.post('http://127.0.0.1:8086/class/addCourse', this.courseForm).then(res => {
          console.log(res);
          this.$message("Reading Courses");
          clearTimeout(this.timer);  //清除延迟执行

          this.timer = setTimeout(()=>{   //设置延迟执行
            console.log('ok');
            this.$router.go(0);
          },5000);
        }).catch(err => {
          console.log(err)
        })
      },
      addCourseArrangement() {
        this.addCAFormVisible = false;
        this.courseArrangementForm.time = this.time1 + '-' + this.time2;
        axios.post('http://127.0.0.1:8086/class/addCourseArrangement', this.courseArrangementForm).then(res => {
          console.log(res);
          this.$message("Reading Courses");
          clearTimeout(this.timer);  //清除延迟执行

          this.timer = setTimeout(()=>{   //设置延迟执行
            console.log('ok');
            this.$router.go(0);
        },5000);
        }).catch(err => {
          console.log(err)
        })
      },
      chooseCourse(index, row) {
        let data = {'userID': this.id, 'caID': row.id}
        axios.post('http://127.0.0.1:8086/select/selectCourse', data).then(res => {
          console.log(res);
          this.$message("Reading Courses");
          clearTimeout(this.timer);  //清除延迟执行

          this.timer = setTimeout(()=>{   //设置延迟执行
            console.log('ok');
            this.$router.go(0);
          },5000);
        }).catch(err => {
          console.log(err)
        })
      },
      deleteSelectCourse(index, row) {
        axios.post('http://127.0.0.1:8086/select/deleteSelectCourse', {'ccID': row.id}).then(res => {
          console.log(res);
          this.$message("Reading Courses");
          clearTimeout(this.timer);  //清除延迟执行

          this.timer = setTimeout(()=>{   //设置延迟执行
            console.log('ok');
            this.$router.go(0);
          },5000);
        }).catch(err => {
          console.log(err)
        })
      },
      getAllSelectData() {
        axios.get('http://127.0.0.1:8086/select/getSelectCourse?userID=' + this.id).then(res => {
          this.selectTable = res.data
        }).catch(err => {
          console.log(err)
        })
      },
      getAllCourseData() {
        axios.get('http://127.0.0.1:8086/class/getAllCourse').then(res => {
          this.courseTable = res.data
        }).catch(err => {
          console.log(err)
        })
      },
      getAllTeacherData() {
        axios.get('http://127.0.0.1:8086/user/getAllTeacher').then(res => {
          this.teacherTable = res.data
        }).catch(err => {
          console.log(err)
        })
      },
      getCourseArrangementData() {
        axios.get('http://127.0.0.1:8086/class/getAllCourseArrangement').then(res => {
          this.courseArrangementTable = res.data
        }).catch(err => {
          console.log(err)
        })
      }
    }
  }
</script>

<style scoped>
  .my-div{
    display:inline-block;
    text-align: left;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 1050px;
  }
  .course-card {
    margin-top: 20px;
    width: 500px;
  }
  .teacher-card {
    margin-top: 20px;
    width: 330px;
  }
  .selected-card {
    margin-top: 20px;
    width: 610px;
  }
</style>
