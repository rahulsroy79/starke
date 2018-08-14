/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeEntityMySuffixUpdateComponent } from 'app/entities/starke-entity-my-suffix/starke-entity-my-suffix-update.component';
import { StarkeEntityMySuffixService } from 'app/entities/starke-entity-my-suffix/starke-entity-my-suffix.service';
import { StarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeEntityMySuffix Management Update Component', () => {
        let comp: StarkeEntityMySuffixUpdateComponent;
        let fixture: ComponentFixture<StarkeEntityMySuffixUpdateComponent>;
        let service: StarkeEntityMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeEntityMySuffixUpdateComponent]
            })
                .overrideTemplate(StarkeEntityMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StarkeEntityMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeEntityMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StarkeEntityMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.starkeEntity = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StarkeEntityMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.starkeEntity = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
