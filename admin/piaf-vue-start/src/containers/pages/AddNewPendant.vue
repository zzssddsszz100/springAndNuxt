<template>
  <b-modal
    id="modalright"
    ref="modalright"
    :title="$t('pendant.add-title')"
    modal-class="modal-right"
  >
    <b-form>
      <b-form-group :label="$t('pendant.name')">
        <b-form-input v-model="newItem.name" />
      </b-form-group>
      <b-form-group :label="$t('pendant.mountingType')">
        <v-select :options="mountingType" v-model="newItem.mountingType" />
      </b-form-group>
      <b-form-group :label="$t('pendant.color')">
        <v-select :options="color" v-model="newItem.color" />
      </b-form-group>
      <b-form-group :label="$t('pendant.material')">
        <v-select :options="material" v-model="newItem.material" />
      </b-form-group>

<!--      <b-form-group :label="$t('pages.description')">
        <b-textarea v-model="newItem.description" :rows="2" :max-rows="2" />
      </b-form-group>
      <b-form-group :label="$t('pages.status')">
        <b-form-radio-group stacked class="pt-2" :options="statuses" v-model="newItem.status" />
      </b-form-group>-->
    </b-form>

    <template slot="modal-footer">
      <b-button
        variant="outline-secondary"
        @click="hideModal('modalright')"
      >{{ $t('pages.cancel') }}</b-button>
      <b-button variant="primary" @click="addNewItem()" class="mr-1">{{ $t('pages.submit') }}</b-button>
    </template>
  </b-modal>
</template>
<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import axios from "axios";
export default {
  components: {
    "v-select": vSelect
  },
  props: ["statuses"],
  data() {
    return {
      newItem: {
        name: "",
        mountingType: "",
        material: "",
        color: "",
        buyPrice: 0,
        stock: 0
      },
      mountingType : ["1고리","2고리","1오링","2오링","통과"],
      color : ["무도금","핑크골드","화이트골드"],
      material : ["실버","14K골드","24K골드"]
    };
  },
  methods: {
    addNewItem() {
      console.log("adding item : ", this.newItem);
      axios.post("/pendants",this.newItem);
    },
    hideModal(refname) {
      this.$refs[refname].hide();
    }
  }
};
</script>

