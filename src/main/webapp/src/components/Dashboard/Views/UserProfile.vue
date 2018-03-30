<template>
    <div class="row">
      <div class="col-lg-4 col-md-5">
        <user-card>
        </user-card>
        <members-card>
        </members-card>
      </div>
      <div class="col-lg-8 col-md-7">
        <edit-profile-form>
        </edit-profile-form>
      </div>
    </div>
</template>
<script>
import EditProfileForm from './UserProfile/EditProfileForm';
import UserCard from './UserProfile/UserCard';
import MembersCard from './UserProfile/MembersCard';

export default {
  components: {
    EditProfileForm,
    UserCard,
    MembersCard,
  },
};
</script>
<style>
</style>
