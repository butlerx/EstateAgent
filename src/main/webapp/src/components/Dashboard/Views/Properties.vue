<template>
  <div class="row">
    <div class="col-md-12">
      <div class="card">
        <div class="filter">
          <input v-model.number="filter.min" placeholder="min price" type="number">
          <input v-model.number="filter.max" placeholder="max price" type="number">
          <button v-on:click="getProperties(filter.min, filter.max)">filter</button>
        </div>
        <paper-table :annotations="annotations" :sub-title="subTitle" :data="rows" :columns="columns">
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
      subTitle: 'List of all properties',
      columns: ['id', 'type', 'bedrooms', 'district', 'start', 'end', 'price'],
      annotations: {
        district: 'D',
        price: '€',
      },
      rows: [],
      filter: {},
    };
  },
  methods: {
    getProperties(min = 0, max = 2147483647) {
      const filter = `?min=${min}&max=${max}`;
      fetch(`/api/property${filter}`)
        .then(response => response.json())
        .then(json => {
          this.rows = json;
        });
    },
  },

  mounted() {
    this.getProperties();
  },
};
</script>
<style style="scss" scoped>
.filter {
  padding: 1em 1em 0;
  display: flex;
  justify-content: flex-end;
}
</style>
