<template>
  <div class="my-div">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>{{$route.params.userId}}  {{$route.params.name}}</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible = true">Add Course</el-button>
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
            prop="teacher"
            label="teacher"
            width="180">
        </el-table-column>
        <el-table-column
            prop="credit"
            label="credit"
            width="180">
        </el-table-column>
        <el-table-column
            prop="time"
            label="time"
            width="250">
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="Add Course" :visible.sync="dialogFormVisible">
      <el-form :model="courseForm">
        <el-form-item label="Course ID" :label-width="formLabelWidth">
          <el-input v-model="courseForm.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Name" :label-width="formLabelWidth">
          <el-input v-model="courseForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Teacher" :label-width="formLabelWidth">
          <el-input v-model="courseForm.teacher" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Credit" :label-width="formLabelWidth">
          <el-input v-model="courseForm.credit" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Course Time" :label-width="formLabelWidth">
          <el-time-picker v-model="time1" style="width: 190px" value-format="HH:mm:ss">
          </el-time-picker>
          <span> To </span>
          <el-time-picker
              v-model="time2"
              style="width: 190px"
              :picker-options="{minTime: time1}"
              value-format="HH:mm:ss">
          </el-time-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
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
        courseTable: [],
        dialogFormVisible: false,
        formLabelWidth: '120px',
        time1: '',
        time2: '',
        courseForm: {
          id: '',
          name: '',
          teacher: '',
          credit: '',
          time: ''
        }
      }
    },
    created() {
      this.getCourseData()
    },
    methods: {
      addCourse() {
        this.dialogFormVisible = false;
        this.courseForm.time = this.time1 + '-' + this.time2;
        axios.post('http://127.0.0.1:8086/test/addCourse', this.courseForm).then(res => {
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
      getCourseData() {
        axios.get('http://127.0.0.1:8086/test/getCourses').then(res => {
          this.courseTable = res.data
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
    text-align: center;
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
    width: 930px;
  }
</style>
