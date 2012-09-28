package add.haslearntit.application.skills;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public class NewSkillPanel extends Panel {

	private static final long serialVersionUID = 2627832833454321010L;

	private final SkillsRepository skillsRepository;

	public NewSkillPanel(String id, SkillsRepository skillsRepository) {

		super(id);
		this.skillsRepository = skillsRepository;
		SkillModel skillModel = new SkillModel();
		add(new NewSkillFrom("newSkillForm", new CompoundPropertyModel<SkillModel>(skillModel)));
	}

	private class NewSkillFrom extends Form {
		private static final long serialVersionUID = -3821156411930577907L;

		public NewSkillFrom(String id, CompoundPropertyModel<SkillModel> compoundPropertyModel) {
			super(id, compoundPropertyModel);
			initializeComponents();
		}

		private void initializeComponents() {
			add(new RequiredTextField<String>("name"));
			add(new RequiredTextField<String>("difficulty"));
			add(new RequiredTextField<String>("time"));
		}

		@Override
		protected void onSubmit() {
			SkillModel model = (SkillModel) this.getDefaultModelObject();
			skillsRepository.store(new Skill(model.name, model.difficulty, model.time));
			this.setDefaultModelObject(new SkillModel());
		}
	}

	private class SkillModel {
		private String name;
		private String difficulty;
		private String time;
	}

}
