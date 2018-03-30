<template>
  <div class="row">
    <div class="col-md-12">
      <div class="card">
        <paper-table type="hover" :title="table1.title" :sub-title="table1.subTitle" :data="table1.data" :columns="table1.columns">
        </paper-table>
      </div>
    </div>
  </div>
</template>
<script>
import PaperTable from 'components/UIComponents/PaperTable';

export default {
  components: {
    PaperTable,
  },
  data() {
    return {
      table1: {
        title: 'Properties',
        subTitle: 'List of all properties',
        columns: [
          'id',
          'type',
          'bedrooms',
          'district',
          'start',
          'end',
          'price',
        ],
        data: [],
      },
    };
  },
  mounted() {
    fetch('/api/property/')
      .then(response => response.json())
      .then(json => {
        this.table1.data = json;
      });
  },
};
</script>
<style>
</style>
